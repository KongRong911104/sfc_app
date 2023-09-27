package DSAES;

import library.library;
import utils.Resources;

import java.io.*;


public class DSAES {
    protected int[][] EK = new int[4][16];
    protected int[][] D_box = new int[4][256];
    protected int[] n = new int[5];

    public DSAES(String PW) {
        int[] pw = library.StringToInt(PW);
        int[] i_Box = {52, 91, 130, 169, 208, 247, 30, 71, 112, 153, 194, 235, 20, 63, 106, 149, 192, 236, 23, 68,
                114, 159, 204, 250, 39, 86, 134, 181, 228, 19, 70, 120, 170, 219, 12, 64, 117, 168, 221, 16, 73,
                126, 180, 234, 34, 90, 146, 203, 4, 61, 123, 183, 242, 45, 105, 167, 230, 37, 100, 164, 229, 40,
                104, 174, 241, 51, 122, 191, 5, 78, 148, 220, 38, 113, 189, 8, 85, 162, 244, 65, 144, 225, 53,
                137, 217, 48, 136, 222, 55, 143, 237, 72, 161, 0, 95, 193, 29, 131, 231, 77, 178, 24, 129, 239,
                88, 198, 49, 163, 18, 138, 254, 111, 233, 98, 216, 92, 213, 89, 215, 97, 232, 110, 253, 135, 21,
                166, 56, 202, 101, 2, 156, 58, 214, 128, 36, 206, 125, 43, 223, 151, 76, 10, 197, 141, 80, 26,
                238, 184, 142, 99, 62, 32, 9, 248, 218, 205, 196, 190, 195, 201, 212, 246, 11, 41, 75, 116, 160,
                224, 31, 102, 175, 14, 107, 200, 66, 177, 59, 187, 93, 3, 165, 96, 42, 252, 207, 176, 171, 173,
                199, 251, 44, 109, 186, 54, 158, 79, 1, 179, 140, 132, 147, 209, 25, 119, 17, 182, 133, 127, 172,
                22, 139, 74, 60, 94, 227, 118, 108, 188, 81, 67, 154, 84, 152, 69, 150, 103, 6, 46, 50, 35, 28,
                47, 145, 57, 157, 226, 7, 240, 87, 83, 82, 13, 210, 121, 15, 33, 27, 245, 211, 124, 115, 155, 185,
                243, 249, 255};
        pw = library.DASM(pw, i_Box);
        int[] KPW = new int[16];
        System.arraycopy(pw, 0, KPW, 0, 16);
        //D0_box
        D_box[0] = library.GBT1(pw, i_Box);
        int[] PW_E = library.DASMExpansion(pw, D_box[0]);
        int[] KL = library.Middle(PW_E, 40, 16);
        int[] KR = library.Middle(PW_E, 72, 16);
        //EK0
        EK[0] = library.XOR(library.BinaryAdd(KPW, KL), KR);
        //D1_box
        D_box[1] = library.GBT1(PW_E, D_box[0]);
        //D2_box,D3_box,EK1,EK2
        for (int i = 1; i < 3; i++) {
            EK[i] = library.DASM(EK[i - 1], D_box[i]);
            D_box[i + 1] = library.GBT2(EK[i], D_box[i]);
        }
        //EK3
        EK[3] = library.DASM(EK[2], D_box[3]);
        for (int i = 0; i < 8; i++) {
            n[1] += EK[0][2 * i];
            n[2] += EK[0][2 * i + 1];
            n[3] += EK[0][i];
            n[4] += EK[0][i + 8];
        }
        n[1] %= 64;
        n[2] %= 64;
        n[3] %= 80;
        n[4] %= 80;
    }

    public String Encryption(String str) {
        int[] P = library.StringToInt(str);
        int[] C1 = library.BinaryAdd(library.Rotate(P, EK[1]), EK[2]);
        C1 = library.SubBytes(library.DBR(C1, n[1]), D_box[1]);
        C1 = library.MixColumn(C1, n[3]);
        int[] C2 = library.XOR(library.Rotate(C1, EK[2]), EK[3]);
        C2 = library.SubBytes(library.DBR(C2, n[2]), D_box[2]);
        C2 = library.MixColumn(C2, n[4]);
        int[] C = library.SubBytes(library.XOR(library.BinaryAdd(C2, EK[3]), EK[1]), D_box[3]);
        return library.IntToString(C);
    }

    public String Decryption(String str) {
        int[] C = library.StringToInt(str);
        int[] C2 = library.BinarySub(library.XOR(library.SubBytes(C, library.InverseBox(D_box[3])), EK[1]), EK[3]);
        C2 = library.InverseMixColumn(C2, n[4]);
        C2 = library.InverseDBR(library.SubBytes(C2, library.InverseBox(D_box[2])), n[2]);
        int[] C1 = library.InverseRotate(library.XOR(C2, EK[3]), EK[2]);
        C1 = library.InverseMixColumn(C1, n[3]);
        C1 = library.InverseDBR(library.SubBytes(C1, library.InverseBox(D_box[1])), n[1]);
        int[] P = library.InverseRotate(library.BinarySub(C1, EK[2]), EK[1]);
        return library.IntToString(P);
    }


    public void FileEncryption(String filePath) {
        try (InputStream inputStream = Resources.get(filePath)) {
            try (OutputStream outputStream = Resources.getOutputStream("enc_" + filePath)) {
                byte[] buffer = new byte[inputStream.available()];
                int[] tmp = new int[16];

                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {


                    int[] FileBlock = library.ByteTOInt(buffer);
                    int group = bytesRead / 16;
                    if (bytesRead % 16 != 0) {
                        group += 1;
                    }
                    int[] output = new int[group*16];
                    for (int i = 0; i < group; i++) {

                        if (i != group - 1) {
                            System.arraycopy(FileBlock, i * 16, tmp, 0, 16);
                        } else {
                            int lack = bytesRead % 16;
                            if (lack != 0) {
                                System.arraycopy(FileBlock, i * 16, tmp, 0, lack);
                                int paddingLength = 16 - lack;
                                for (int j = 0; j < paddingLength; j++) {
                                    tmp[lack + j] = 4;
                                }
                            } else {
                                System.arraycopy(FileBlock, i * 16, tmp, 0, 16);
                            }
                        }

                        int[] enc = library.StringToInt(this.Encryption(library.IntToString(tmp)));
                        System.arraycopy(enc, 0, output, i * 16, 16);
                    }
                    outputStream.write(library.IntTOByte(output));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FileDecryption(String filePath)  {
        try (InputStream inputStream = Resources.get(filePath)) {
            try (OutputStream outputStream = Resources.getOutputStream("dec_" + filePath)) {
                byte[] buffer = new byte[inputStream.available()];
                int[] tmp = new int[16];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    int[] FileBlock = library.ByteTOInt(buffer);
                    if (bytesRead > 16) {
                        int[] output = new int[bytesRead - 16];
                        int group = bytesRead / 16;
                        for (int i = 0; i < group; i++) {
                            System.arraycopy(FileBlock, i * 16, tmp, 0, 16);
                            int[] dec = library.StringToInt(this.Decryption(library.IntToString(tmp)));
                            if (i == group - 1) {
//                                int[] ori;
                                int end = 15;
                                if (dec[15] == 4) {
                                    for (int j = 14; j >= 0; j--) {
                                        if (dec[j] != 4) {
                                            end = j;
                                            break;
                                        }
                                    }
                                }
                                int[] combined = new int[end + bytesRead - 15];
                                System.arraycopy(output, 0, combined, 0, bytesRead - 16);
                                System.arraycopy(dec, 0, combined, bytesRead - 16, end + 1);
                                outputStream.write(library.IntTOByte(combined));
                                break;
                            }
                            System.arraycopy(dec, 0, output, i * 16, 16);
                        }
                    } else {
                        System.arraycopy(FileBlock, 0, tmp, 0, 16);
                        int[] dec = library.StringToInt(this.Decryption(library.IntToString(tmp)));
                        int[] ori;
                        int end = 15;
                        if (dec[15] == 4) {
                            for (int j = 14; j >= 0; j--) {
                                if (dec[j] != 4) {
                                    end = j;
                                    break;
                                }
                            }
                        }
                        ori = new int[end + 1];
                        System.arraycopy(dec, 0, ori, 0, end + 1);
                        outputStream.write(library.IntTOByte(ori));
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DSAES dsaes = new DSAES("vbitsnbmc;oasd;vlmsfdvdafb");
        dsaes.FileEncryption(".\\src\\main\\java\\txt_file\\1.txt");
        dsaes.FileDecryption(".\\src\\main\\java\\DSAES\\DSAESEncryption\\1.txt");
//        String x = "sixsquare1234567";
//        DSAES D= new DSAES("vbitsnbmc;oasd;vlmsfdvdafb");
//        String str = D.Encryption(x);
//        System.out.println(str);
//        System.out.println(D.Decryption(str));
    }
}
