package library;


public class MixColumnTable {
    public static final int[][] MulTable =
            {
                    {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 124, 126, 128, 130, 132, 134, 136, 138, 140, 142, 144, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180, 182, 184, 186, 188, 190, 192, 194, 196, 198, 200, 202, 204, 206, 208, 210, 212, 214, 216, 218, 220, 222, 224, 226, 228, 230, 232, 234, 236, 238, 240, 242, 244, 246, 248, 250, 252, 254, 27, 25, 31, 29, 19, 17, 23, 21, 11, 9, 15, 13, 3, 1, 7, 5, 59, 57, 63, 61, 51, 49, 55, 53, 43, 41, 47, 45, 35, 33, 39, 37, 91, 89, 95, 93, 83, 81, 87, 85, 75, 73, 79, 77, 67, 65, 71, 69, 123, 121, 127, 125, 115, 113, 119, 117, 107, 105, 111, 109, 99, 97, 103, 101, 155, 153, 159, 157, 147, 145, 151, 149, 139, 137, 143, 141, 131, 129, 135, 133, 187, 185, 191, 189, 179, 177, 183, 181, 171, 169, 175, 173, 163, 161, 167, 165, 219, 217, 223, 221, 211, 209, 215, 213, 203, 201, 207, 205, 195, 193, 199, 197, 251, 249, 255, 253, 243, 241, 247, 245, 235, 233, 239, 237, 227, 225, 231, 229},
                    {0, 3, 6, 5, 12, 15, 10, 9, 24, 27, 30, 29, 20, 23, 18, 17, 48, 51, 54, 53, 60, 63, 58, 57, 40, 43, 46, 45, 36, 39, 34, 33, 96, 99, 102, 101, 108, 111, 106, 105, 120, 123, 126, 125, 116, 119, 114, 113, 80, 83, 86, 85, 92, 95, 90, 89, 72, 75, 78, 77, 68, 71, 66, 65, 192, 195, 198, 197, 204, 207, 202, 201, 216, 219, 222, 221, 212, 215, 210, 209, 240, 243, 246, 245, 252, 255, 250, 249, 232, 235, 238, 237, 228, 231, 226, 225, 160, 163, 166, 165, 172, 175, 170, 169, 184, 187, 190, 189, 180, 183, 178, 177, 144, 147, 150, 149, 156, 159, 154, 153, 136, 139, 142, 141, 132, 135, 130, 129, 155, 152, 157, 158, 151, 148, 145, 146, 131, 128, 133, 134, 143, 140, 137, 138, 171, 168, 173, 174, 167, 164, 161, 162, 179, 176, 181, 182, 191, 188, 185, 186, 251, 248, 253, 254, 247, 244, 241, 242, 227, 224, 229, 230, 239, 236, 233, 234, 203, 200, 205, 206, 199, 196, 193, 194, 211, 208, 213, 214, 223, 220, 217, 218, 91, 88, 93, 94, 87, 84, 81, 82, 67, 64, 69, 70, 79, 76, 73, 74, 107, 104, 109, 110, 103, 100, 97, 98, 115, 112, 117, 118, 127, 124, 121, 122, 59, 56, 61, 62, 55, 52, 49, 50, 35, 32, 37, 38, 47, 44, 41, 42, 11, 8, 13, 14, 7, 4, 1, 2, 19, 16, 21, 22, 31, 28, 25, 26},
                    {0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88, 92, 96, 100, 104, 108, 112, 116, 120, 124, 128, 132, 136, 140, 144, 148, 152, 156, 160, 164, 168, 172, 176, 180, 184, 188, 192, 196, 200, 204, 208, 212, 216, 220, 224, 228, 232, 236, 240, 244, 248, 252, 27, 31, 19, 23, 11, 15, 3, 7, 59, 63, 51, 55, 43, 47, 35, 39, 91, 95, 83, 87, 75, 79, 67, 71, 123, 127, 115, 119, 107, 111, 99, 103, 155, 159, 147, 151, 139, 143, 131, 135, 187, 191, 179, 183, 171, 175, 163, 167, 219, 223, 211, 215, 203, 207, 195, 199, 251, 255, 243, 247, 235, 239, 227, 231, 54, 50, 62, 58, 38, 34, 46, 42, 22, 18, 30, 26, 6, 2, 14, 10, 118, 114, 126, 122, 102, 98, 110, 106, 86, 82, 94, 90, 70, 66, 78, 74, 182, 178, 190, 186, 166, 162, 174, 170, 150, 146, 158, 154, 134, 130, 142, 138, 246, 242, 254, 250, 230, 226, 238, 234, 214, 210, 222, 218, 198, 194, 206, 202, 45, 41, 37, 33, 61, 57, 53, 49, 13, 9, 5, 1, 29, 25, 21, 17, 109, 105, 101, 97, 125, 121, 117, 113, 77, 73, 69, 65, 93, 89, 85, 81, 173, 169, 165, 161, 189, 185, 181, 177, 141, 137, 133, 129, 157, 153, 149, 145, 237, 233, 229, 225, 253, 249, 245, 241, 205, 201, 197, 193, 221, 217, 213, 209},
                    {0, 5, 10, 15, 20, 17, 30, 27, 40, 45, 34, 39, 60, 57, 54, 51, 80, 85, 90, 95, 68, 65, 78, 75, 120, 125, 114, 119, 108, 105, 102, 99, 160, 165, 170, 175, 180, 177, 190, 187, 136, 141, 130, 135, 156, 153, 150, 147, 240, 245, 250, 255, 228, 225, 238, 235, 216, 221, 210, 215, 204, 201, 198, 195, 91, 94, 81, 84, 79, 74, 69, 64, 115, 118, 121, 124, 103, 98, 109, 104, 11, 14, 1, 4, 31, 26, 21, 16, 35, 38, 41, 44, 55, 50, 61, 56, 251, 254, 241, 244, 239, 234, 229, 224, 211, 214, 217, 220, 199, 194, 205, 200, 171, 174, 161, 164, 191, 186, 181, 176, 131, 134, 137, 140, 151, 146, 157, 152, 182, 179, 188, 185, 162, 167, 168, 173, 158, 155, 148, 145, 138, 143, 128, 133, 230, 227, 236, 233, 242, 247, 248, 253, 206, 203, 196, 193, 218, 223, 208, 213, 22, 19, 28, 25, 2, 7, 8, 13, 62, 59, 52, 49, 42, 47, 32, 37, 70, 67, 76, 73, 82, 87, 88, 93, 110, 107, 100, 97, 122, 127, 112, 117, 237, 232, 231, 226, 249, 252, 243, 246, 197, 192, 207, 202, 209, 212, 219, 222, 189, 184, 183, 178, 169, 172, 163, 166, 149, 144, 159, 154, 129, 132, 139, 142, 77, 72, 71, 66, 89, 92, 83, 86, 101, 96, 111, 106, 113, 116, 123, 126, 29, 24, 23, 18, 9, 12, 3, 6, 53, 48, 63, 58, 33, 36, 43, 46},
                    {0, 6, 12, 10, 24, 30, 20, 18, 48, 54, 60, 58, 40, 46, 36, 34, 96, 102, 108, 106, 120, 126, 116, 114, 80, 86, 92, 90, 72, 78, 68, 66, 192, 198, 204, 202, 216, 222, 212, 210, 240, 246, 252, 250, 232, 238, 228, 226, 160, 166, 172, 170, 184, 190, 180, 178, 144, 150, 156, 154, 136, 142, 132, 130, 155, 157, 151, 145, 131, 133, 143, 137, 171, 173, 167, 161, 179, 181, 191, 185, 251, 253, 247, 241, 227, 229, 239, 233, 203, 205, 199, 193, 211, 213, 223, 217, 91, 93, 87, 81, 67, 69, 79, 73, 107, 109, 103, 97, 115, 117, 127, 121, 59, 61, 55, 49, 35, 37, 47, 41, 11, 13, 7, 1, 19, 21, 31, 25, 45, 43, 33, 39, 53, 51, 57, 63, 29, 27, 17, 23, 5, 3, 9, 15, 77, 75, 65, 71, 85, 83, 89, 95, 125, 123, 113, 119, 101, 99, 105, 111, 237, 235, 225, 231, 245, 243, 249, 255, 221, 219, 209, 215, 197, 195, 201, 207, 141, 139, 129, 135, 149, 147, 153, 159, 189, 187, 177, 183, 165, 163, 169, 175, 182, 176, 186, 188, 174, 168, 162, 164, 134, 128, 138, 140, 158, 152, 146, 148, 214, 208, 218, 220, 206, 200, 194, 196, 230, 224, 234, 236, 254, 248, 242, 244, 118, 112, 122, 124, 110, 104, 98, 100, 70, 64, 74, 76, 94, 88, 82, 84, 22, 16, 26, 28, 14, 8, 2, 4, 38, 32, 42, 44, 62, 56, 50, 52},
                    {0, 7, 14, 9, 28, 27, 18, 21, 56, 63, 54, 49, 36, 35, 42, 45, 112, 119, 126, 121, 108, 107, 98, 101, 72, 79, 70, 65, 84, 83, 90, 93, 224, 231, 238, 233, 252, 251, 242, 245, 216, 223, 214, 209, 196, 195, 202, 205, 144, 151, 158, 153, 140, 139, 130, 133, 168, 175, 166, 161, 180, 179, 186, 189, 219, 220, 213, 210, 199, 192, 201, 206, 227, 228, 237, 234, 255, 248, 241, 246, 171, 172, 165, 162, 183, 176, 185, 190, 147, 148, 157, 154, 143, 136, 129, 134, 59, 60, 53, 50, 39, 32, 41, 46, 3, 4, 13, 10, 31, 24, 17, 22, 75, 76, 69, 66, 87, 80, 89, 94, 115, 116, 125, 122, 111, 104, 97, 102, 173, 170, 163, 164, 177, 182, 191, 184, 149, 146, 155, 156, 137, 142, 135, 128, 221, 218, 211, 212, 193, 198, 207, 200, 229, 226, 235, 236, 249, 254, 247, 240, 77, 74, 67, 68, 81, 86, 95, 88, 117, 114, 123, 124, 105, 110, 103, 96, 61, 58, 51, 52, 33, 38, 47, 40, 5, 2, 11, 12, 25, 30, 23, 16, 118, 113, 120, 127, 106, 109, 100, 99, 78, 73, 64, 71, 82, 85, 92, 91, 6, 1, 8, 15, 26, 29, 20, 19, 62, 57, 48, 55, 34, 37, 44, 43, 150, 145, 152, 159, 138, 141, 132, 131, 174, 169, 160, 167, 178, 181, 188, 187, 230, 225, 232, 239, 250, 253, 244, 243, 222, 217, 208, 215, 194, 197, 204, 203},
                    {0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 120, 128, 136, 144, 152, 160, 168, 176, 184, 192, 200, 208, 216, 224, 232, 240, 248, 27, 19, 11, 3, 59, 51, 43, 35, 91, 83, 75, 67, 123, 115, 107, 99, 155, 147, 139, 131, 187, 179, 171, 163, 219, 211, 203, 195, 251, 243, 235, 227, 54, 62, 38, 46, 22, 30, 6, 14, 118, 126, 102, 110, 86, 94, 70, 78, 182, 190, 166, 174, 150, 158, 134, 142, 246, 254, 230, 238, 214, 222, 198, 206, 45, 37, 61, 53, 13, 5, 29, 21, 109, 101, 125, 117, 77, 69, 93, 85, 173, 165, 189, 181, 141, 133, 157, 149, 237, 229, 253, 245, 205, 197, 221, 213, 108, 100, 124, 116, 76, 68, 92, 84, 44, 36, 60, 52, 12, 4, 28, 20, 236, 228, 252, 244, 204, 196, 220, 212, 172, 164, 188, 180, 140, 132, 156, 148, 119, 127, 103, 111, 87, 95, 71, 79, 55, 63, 39, 47, 23, 31, 7, 15, 247, 255, 231, 239, 215, 223, 199, 207, 183, 191, 167, 175, 151, 159, 135, 143, 90, 82, 74, 66, 122, 114, 106, 98, 26, 18, 10, 2, 58, 50, 42, 34, 218, 210, 202, 194, 250, 242, 234, 226, 154, 146, 138, 130, 186, 178, 170, 162, 65, 73, 81, 89, 97, 105, 113, 121, 1, 9, 17, 25, 33, 41, 49, 57, 193, 201, 209, 217, 225, 233, 241, 249, 129, 137, 145, 153, 161, 169, 177, 185},
                    {0, 9, 18, 27, 36, 45, 54, 63, 72, 65, 90, 83, 108, 101, 126, 119, 144, 153, 130, 139, 180, 189, 166, 175, 216, 209, 202, 195, 252, 245, 238, 231, 59, 50, 41, 32, 31, 22, 13, 4, 115, 122, 97, 104, 87, 94, 69, 76, 171, 162, 185, 176, 143, 134, 157, 148, 227, 234, 241, 248, 199, 206, 213, 220, 118, 127, 100, 109, 82, 91, 64, 73, 62, 55, 44, 37, 26, 19, 8, 1, 230, 239, 244, 253, 194, 203, 208, 217, 174, 167, 188, 181, 138, 131, 152, 145, 77, 68, 95, 86, 105, 96, 123, 114, 5, 12, 23, 30, 33, 40, 51, 58, 221, 212, 207, 198, 249, 240, 235, 226, 149, 156, 135, 142, 177, 184, 163, 170, 236, 229, 254, 247, 200, 193, 218, 211, 164, 173, 182, 191, 128, 137, 146, 155, 124, 117, 110, 103, 88, 81, 74, 67, 52, 61, 38, 47, 16, 25, 2, 11, 215, 222, 197, 204, 243, 250, 225, 232, 159, 150, 141, 132, 187, 178, 169, 160, 71, 78, 85, 92, 99, 106, 113, 120, 15, 6, 29, 20, 43, 34, 57, 48, 154, 147, 136, 129, 190, 183, 172, 165, 210, 219, 192, 201, 246, 255, 228, 237, 10, 3, 24, 17, 46, 39, 60, 53, 66, 75, 80, 89, 102, 111, 116, 125, 161, 168, 179, 186, 133, 140, 151, 158, 233, 224, 251, 242, 205, 196, 223, 214, 49, 56, 35, 42, 21, 28, 7, 14, 121, 112, 107, 98, 93, 84, 79, 70},
                    {0, 10, 20, 30, 40, 34, 60, 54, 80, 90, 68, 78, 120, 114, 108, 102, 160, 170, 180, 190, 136, 130, 156, 150, 240, 250, 228, 238, 216, 210, 204, 198, 91, 81, 79, 69, 115, 121, 103, 109, 11, 1, 31, 21, 35, 41, 55, 61, 251, 241, 239, 229, 211, 217, 199, 205, 171, 161, 191, 181, 131, 137, 151, 157, 182, 188, 162, 168, 158, 148, 138, 128, 230, 236, 242, 248, 206, 196, 218, 208, 22, 28, 2, 8, 62, 52, 42, 32, 70, 76, 82, 88, 110, 100, 122, 112, 237, 231, 249, 243, 197, 207, 209, 219, 189, 183, 169, 163, 149, 159, 129, 139, 77, 71, 89, 83, 101, 111, 113, 123, 29, 23, 9, 3, 53, 63, 33, 43, 119, 125, 99, 105, 95, 85, 75, 65, 39, 45, 51, 57, 15, 5, 27, 17, 215, 221, 195, 201, 255, 245, 235, 225, 135, 141, 147, 153, 175, 165, 187, 177, 44, 38, 56, 50, 4, 14, 16, 26, 124, 118, 104, 98, 84, 94, 64, 74, 140, 134, 152, 146, 164, 174, 176, 186, 220, 214, 200, 194, 244, 254, 224, 234, 193, 203, 213, 223, 233, 227, 253, 247, 145, 155, 133, 143, 185, 179, 173, 167, 97, 107, 117, 127, 73, 67, 93, 87, 49, 59, 37, 47, 25, 19, 13, 7, 154, 144, 142, 132, 178, 184, 166, 172, 202, 192, 222, 212, 226, 232, 246, 252, 58, 48, 46, 36, 18, 24, 6, 12, 106, 96, 126, 116, 66, 72, 86, 92},
                    {0, 11, 22, 29, 44, 39, 58, 49, 88, 83, 78, 69, 116, 127, 98, 105, 176, 187, 166, 173, 156, 151, 138, 129, 232, 227, 254, 245, 196, 207, 210, 217, 123, 112, 109, 102, 87, 92, 65, 74, 35, 40, 53, 62, 15, 4, 25, 18, 203, 192, 221, 214, 231, 236, 241, 250, 147, 152, 133, 142, 191, 180, 169, 162, 246, 253, 224, 235, 218, 209, 204, 199, 174, 165, 184, 179, 130, 137, 148, 159, 70, 77, 80, 91, 106, 97, 124, 119, 30, 21, 8, 3, 50, 57, 36, 47, 141, 134, 155, 144, 161, 170, 183, 188, 213, 222, 195, 200, 249, 242, 239, 228, 61, 54, 43, 32, 17, 26, 7, 12, 101, 110, 115, 120, 73, 66, 95, 84, 247, 252, 225, 234, 219, 208, 205, 198, 175, 164, 185, 178, 131, 136, 149, 158, 71, 76, 81, 90, 107, 96, 125, 118, 31, 20, 9, 2, 51, 56, 37, 46, 140, 135, 154, 145, 160, 171, 182, 189, 212, 223, 194, 201, 248, 243, 238, 229, 60, 55, 42, 33, 16, 27, 6, 13, 100, 111, 114, 121, 72, 67, 94, 85, 1, 10, 23, 28, 45, 38, 59, 48, 89, 82, 79, 68, 117, 126, 99, 104, 177, 186, 167, 172, 157, 150, 139, 128, 233, 226, 255, 244, 197, 206, 211, 216, 122, 113, 108, 103, 86, 93, 64, 75, 34, 41, 52, 63, 14, 5, 24, 19, 202, 193, 220, 215, 230, 237, 240, 251, 146, 153, 132, 143, 190, 181, 168, 163},
                    {0, 12, 24, 20, 48, 60, 40, 36, 96, 108, 120, 116, 80, 92, 72, 68, 192, 204, 216, 212, 240, 252, 232, 228, 160, 172, 184, 180, 144, 156, 136, 132, 155, 151, 131, 143, 171, 167, 179, 191, 251, 247, 227, 239, 203, 199, 211, 223, 91, 87, 67, 79, 107, 103, 115, 127, 59, 55, 35, 47, 11, 7, 19, 31, 45, 33, 53, 57, 29, 17, 5, 9, 77, 65, 85, 89, 125, 113, 101, 105, 237, 225, 245, 249, 221, 209, 197, 201, 141, 129, 149, 153, 189, 177, 165, 169, 182, 186, 174, 162, 134, 138, 158, 146, 214, 218, 206, 194, 230, 234, 254, 242, 118, 122, 110, 98, 70, 74, 94, 82, 22, 26, 14, 2, 38, 42, 62, 50, 90, 86, 66, 78, 106, 102, 114, 126, 58, 54, 34, 46, 10, 6, 18, 30, 154, 150, 130, 142, 170, 166, 178, 190, 250, 246, 226, 238, 202, 198, 210, 222, 193, 205, 217, 213, 241, 253, 233, 229, 161, 173, 185, 181, 145, 157, 137, 133, 1, 13, 25, 21, 49, 61, 41, 37, 97, 109, 121, 117, 81, 93, 73, 69, 119, 123, 111, 99, 71, 75, 95, 83, 23, 27, 15, 3, 39, 43, 63, 51, 183, 187, 175, 163, 135, 139, 159, 147, 215, 219, 207, 195, 231, 235, 255, 243, 236, 224, 244, 248, 220, 208, 196, 200, 140, 128, 148, 152, 188, 176, 164, 168, 44, 32, 52, 56, 28, 16, 4, 8, 76, 64, 84, 88, 124, 112, 100, 104},
                    {0, 13, 26, 23, 52, 57, 46, 35, 104, 101, 114, 127, 92, 81, 70, 75, 208, 221, 202, 199, 228, 233, 254, 243, 184, 181, 162, 175, 140, 129, 150, 155, 187, 182, 161, 172, 143, 130, 149, 152, 211, 222, 201, 196, 231, 234, 253, 240, 107, 102, 113, 124, 95, 82, 69, 72, 3, 14, 25, 20, 55, 58, 45, 32, 109, 96, 119, 122, 89, 84, 67, 78, 5, 8, 31, 18, 49, 60, 43, 38, 189, 176, 167, 170, 137, 132, 147, 158, 213, 216, 207, 194, 225, 236, 251, 246, 214, 219, 204, 193, 226, 239, 248, 245, 190, 179, 164, 169, 138, 135, 144, 157, 6, 11, 28, 17, 50, 63, 40, 37, 110, 99, 116, 121, 90, 87, 64, 77, 218, 215, 192, 205, 238, 227, 244, 249, 178, 191, 168, 165, 134, 139, 156, 145, 10, 7, 16, 29, 62, 51, 36, 41, 98, 111, 120, 117, 86, 91, 76, 65, 97, 108, 123, 118, 85, 88, 79, 66, 9, 4, 19, 30, 61, 48, 39, 42, 177, 188, 171, 166, 133, 136, 159, 146, 217, 212, 195, 206, 237, 224, 247, 250, 183, 186, 173, 160, 131, 142, 153, 148, 223, 210, 197, 200, 235, 230, 241, 252, 103, 106, 125, 112, 83, 94, 73, 68, 15, 2, 21, 24, 59, 54, 33, 44, 12, 1, 22, 27, 56, 53, 34, 47, 100, 105, 126, 115, 80, 93, 74, 71, 220, 209, 198, 203, 232, 229, 242, 255, 180, 185, 174, 163, 128, 141, 154, 151},
                    {0, 14, 28, 18, 56, 54, 36, 42, 112, 126, 108, 98, 72, 70, 84, 90, 224, 238, 252, 242, 216, 214, 196, 202, 144, 158, 140, 130, 168, 166, 180, 186, 219, 213, 199, 201, 227, 237, 255, 241, 171, 165, 183, 185, 147, 157, 143, 129, 59, 53, 39, 41, 3, 13, 31, 17, 75, 69, 87, 89, 115, 125, 111, 97, 173, 163, 177, 191, 149, 155, 137, 135, 221, 211, 193, 207, 229, 235, 249, 247, 77, 67, 81, 95, 117, 123, 105, 103, 61, 51, 33, 47, 5, 11, 25, 23, 118, 120, 106, 100, 78, 64, 82, 92, 6, 8, 26, 20, 62, 48, 34, 44, 150, 152, 138, 132, 174, 160, 178, 188, 230, 232, 250, 244, 222, 208, 194, 204, 65, 79, 93, 83, 121, 119, 101, 107, 49, 63, 45, 35, 9, 7, 21, 27, 161, 175, 189, 179, 153, 151, 133, 139, 209, 223, 205, 195, 233, 231, 245, 251, 154, 148, 134, 136, 162, 172, 190, 176, 234, 228, 246, 248, 210, 220, 206, 192, 122, 116, 102, 104, 66, 76, 94, 80, 10, 4, 22, 24, 50, 60, 46, 32, 236, 226, 240, 254, 212, 218, 200, 198, 156, 146, 128, 142, 164, 170, 184, 182, 12, 2, 16, 30, 52, 58, 40, 38, 124, 114, 96, 110, 68, 74, 88, 86, 55, 57, 43, 37, 15, 1, 19, 29, 71, 73, 91, 85, 127, 113, 99, 109, 215, 217, 203, 197, 239, 225, 243, 253, 167, 169, 187, 181, 159, 145, 131, 141},
                    {0, 15, 30, 17, 60, 51, 34, 45, 120, 119, 102, 105, 68, 75, 90, 85, 240, 255, 238, 225, 204, 195, 210, 221, 136, 135, 150, 153, 180, 187, 170, 165, 251, 244, 229, 234, 199, 200, 217, 214, 131, 140, 157, 146, 191, 176, 161, 174, 11, 4, 21, 26, 55, 56, 41, 38, 115, 124, 109, 98, 79, 64, 81, 94, 237, 226, 243, 252, 209, 222, 207, 192, 149, 154, 139, 132, 169, 166, 183, 184, 29, 18, 3, 12, 33, 46, 63, 48, 101, 106, 123, 116, 89, 86, 71, 72, 22, 25, 8, 7, 42, 37, 52, 59, 110, 97, 112, 127, 82, 93, 76, 67, 230, 233, 248, 247, 218, 213, 196, 203, 158, 145, 128, 143, 162, 173, 188, 179, 193, 206, 223, 208, 253, 242, 227, 236, 185, 182, 167, 168, 133, 138, 155, 148, 49, 62, 47, 32, 13, 2, 19, 28, 73, 70, 87, 88, 117, 122, 107, 100, 58, 53, 36, 43, 6, 9, 24, 23, 66, 77, 92, 83, 126, 113, 96, 111, 202, 197, 212, 219, 246, 249, 232, 231, 178, 189, 172, 163, 142, 129, 144, 159, 44, 35, 50, 61, 16, 31, 14, 1, 84, 91, 74, 69, 104, 103, 118, 121, 220, 211, 194, 205, 224, 239, 254, 241, 164, 171, 186, 181, 152, 151, 134, 137, 215, 216, 201, 198, 235, 228, 245, 250, 175, 160, 177, 190, 147, 156, 141, 130, 39, 40, 57, 54, 27, 20, 5, 10, 95, 80, 65, 78, 99, 108, 125, 114},
                    {0, 16, 32, 48, 64, 80, 96, 112, 128, 144, 160, 176, 192, 208, 224, 240, 27, 11, 59, 43, 91, 75, 123, 107, 155, 139, 187, 171, 219, 203, 251, 235, 54, 38, 22, 6, 118, 102, 86, 70, 182, 166, 150, 134, 246, 230, 214, 198, 45, 61, 13, 29, 109, 125, 77, 93, 173, 189, 141, 157, 237, 253, 205, 221, 108, 124, 76, 92, 44, 60, 12, 28, 236, 252, 204, 220, 172, 188, 140, 156, 119, 103, 87, 71, 55, 39, 23, 7, 247, 231, 215, 199, 183, 167, 151, 135, 90, 74, 122, 106, 26, 10, 58, 42, 218, 202, 250, 234, 154, 138, 186, 170, 65, 81, 97, 113, 1, 17, 33, 49, 193, 209, 225, 241, 129, 145, 161, 177, 216, 200, 248, 232, 152, 136, 184, 168, 88, 72, 120, 104, 24, 8, 56, 40, 195, 211, 227, 243, 131, 147, 163, 179, 67, 83, 99, 115, 3, 19, 35, 51, 238, 254, 206, 222, 174, 190, 142, 158, 110, 126, 78, 94, 46, 62, 14, 30, 245, 229, 213, 197, 181, 165, 149, 133, 117, 101, 85, 69, 53, 37, 21, 5, 180, 164, 148, 132, 244, 228, 212, 196, 52, 36, 20, 4, 116, 100, 84, 68, 175, 191, 143, 159, 239, 255, 207, 223, 47, 63, 15, 31, 111, 127, 79, 95, 130, 146, 162, 178, 194, 210, 226, 242, 2, 18, 34, 50, 66, 82, 98, 114, 153, 137, 185, 169, 217, 201, 249, 233, 25, 9, 57, 41, 89, 73, 121, 105},
                    {0, 17, 34, 51, 68, 85, 102, 119, 136, 153, 170, 187, 204, 221, 238, 255, 11, 26, 41, 56, 79, 94, 109, 124, 131, 146, 161, 176, 199, 214, 229, 244, 22, 7, 52, 37, 82, 67, 112, 97, 158, 143, 188, 173, 218, 203, 248, 233, 29, 12, 63, 46, 89, 72, 123, 106, 149, 132, 183, 166, 209, 192, 243, 226, 44, 61, 14, 31, 104, 121, 74, 91, 164, 181, 134, 151, 224, 241, 194, 211, 39, 54, 5, 20, 99, 114, 65, 80, 175, 190, 141, 156, 235, 250, 201, 216, 58, 43, 24, 9, 126, 111, 92, 77, 178, 163, 144, 129, 246, 231, 212, 197, 49, 32, 19, 2, 117, 100, 87, 70, 185, 168, 155, 138, 253, 236, 223, 206, 88, 73, 122, 107, 28, 13, 62, 47, 208, 193, 242, 227, 148, 133, 182, 167, 83, 66, 113, 96, 23, 6, 53, 36, 219, 202, 249, 232, 159, 142, 189, 172, 78, 95, 108, 125, 10, 27, 40, 57, 198, 215, 228, 245, 130, 147, 160, 177, 69, 84, 103, 118, 1, 16, 35, 50, 205, 220, 239, 254, 137, 152, 171, 186, 116, 101, 86, 71, 48, 33, 18, 3, 252, 237, 222, 207, 184, 169, 154, 139, 127, 110, 93, 76, 59, 42, 25, 8, 247, 230, 213, 196, 179, 162, 145, 128, 98, 115, 64, 81, 38, 55, 4, 21, 234, 251, 200, 217, 174, 191, 140, 157, 105, 120, 75, 90, 45, 60, 15, 30, 225, 240, 195, 210, 165, 180, 135, 150},
                    {0, 18, 36, 54, 72, 90, 108, 126, 144, 130, 180, 166, 216, 202, 252, 238, 59, 41, 31, 13, 115, 97, 87, 69, 171, 185, 143, 157, 227, 241, 199, 213, 118, 100, 82, 64, 62, 44, 26, 8, 230, 244, 194, 208, 174, 188, 138, 152, 77, 95, 105, 123, 5, 23, 33, 51, 221, 207, 249, 235, 149, 135, 177, 163, 236, 254, 200, 218, 164, 182, 128, 146, 124, 110, 88, 74, 52, 38, 16, 2, 215, 197, 243, 225, 159, 141, 187, 169, 71, 85, 99, 113, 15, 29, 43, 57, 154, 136, 190, 172, 210, 192, 246, 228, 10, 24, 46, 60, 66, 80, 102, 116, 161, 179, 133, 151, 233, 251, 205, 223, 49, 35, 21, 7, 121, 107, 93, 79, 195, 209, 231, 245, 139, 153, 175, 189, 83, 65, 119, 101, 27, 9, 63, 45, 248, 234, 220, 206, 176, 162, 148, 134, 104, 122, 76, 94, 32, 50, 4, 22, 181, 167, 145, 131, 253, 239, 217, 203, 37, 55, 1, 19, 109, 127, 73, 91, 142, 156, 170, 184, 198, 212, 226, 240, 30, 12, 58, 40, 86, 68, 114, 96, 47, 61, 11, 25, 103, 117, 67, 81, 191, 173, 155, 137, 247, 229, 211, 193, 20, 6, 48, 34, 92, 78, 120, 106, 132, 150, 160, 178, 204, 222, 232, 250, 89, 75, 125, 111, 17, 3, 53, 39, 201, 219, 237, 255, 129, 147, 165, 183, 98, 112, 70, 84, 42, 56, 14, 28, 242, 224, 214, 196, 186, 168, 158, 140},
                    {0, 19, 38, 53, 76, 95, 106, 121, 152, 139, 190, 173, 212, 199, 242, 225, 43, 56, 13, 30, 103, 116, 65, 82, 179, 160, 149, 134, 255, 236, 217, 202, 86, 69, 112, 99, 26, 9, 60, 47, 206, 221, 232, 251, 130, 145, 164, 183, 125, 110, 91, 72, 49, 34, 23, 4, 229, 246, 195, 208, 169, 186, 143, 156, 172, 191, 138, 153, 224, 243, 198, 213, 52, 39, 18, 1, 120, 107, 94, 77, 135, 148, 161, 178, 203, 216, 237, 254, 31, 12, 57, 42, 83, 64, 117, 102, 250, 233, 220, 207, 182, 165, 144, 131, 98, 113, 68, 87, 46, 61, 8, 27, 209, 194, 247, 228, 157, 142, 187, 168, 73, 90, 111, 124, 5, 22, 35, 48, 67, 80, 101, 118, 15, 28, 41, 58, 219, 200, 253, 238, 151, 132, 177, 162, 104, 123, 78, 93, 36, 55, 2, 17, 240, 227, 214, 197, 188, 175, 154, 137, 21, 6, 51, 32, 89, 74, 127, 108, 141, 158, 171, 184, 193, 210, 231, 244, 62, 45, 24, 11, 114, 97, 84, 71, 166, 181, 128, 147, 234, 249, 204, 223, 239, 252, 201, 218, 163, 176, 133, 150, 119, 100, 81, 66, 59, 40, 29, 14, 196, 215, 226, 241, 136, 155, 174, 189, 92, 79, 122, 105, 16, 3, 54, 37, 185, 170, 159, 140, 245, 230, 211, 192, 33, 50, 7, 20, 109, 126, 75, 88, 146, 129, 180, 167, 222, 205, 248, 235, 10, 25, 44, 63, 70, 85, 96, 115},
                    {0, 20, 40, 60, 80, 68, 120, 108, 160, 180, 136, 156, 240, 228, 216, 204, 91, 79, 115, 103, 11, 31, 35, 55, 251, 239, 211, 199, 171, 191, 131, 151, 182, 162, 158, 138, 230, 242, 206, 218, 22, 2, 62, 42, 70, 82, 110, 122, 237, 249, 197, 209, 189, 169, 149, 129, 77, 89, 101, 113, 29, 9, 53, 33, 119, 99, 95, 75, 39, 51, 15, 27, 215, 195, 255, 235, 135, 147, 175, 187, 44, 56, 4, 16, 124, 104, 84, 64, 140, 152, 164, 176, 220, 200, 244, 224, 193, 213, 233, 253, 145, 133, 185, 173, 97, 117, 73, 93, 49, 37, 25, 13, 154, 142, 178, 166, 202, 222, 226, 246, 58, 46, 18, 6, 106, 126, 66, 86, 238, 250, 198, 210, 190, 170, 150, 130, 78, 90, 102, 114, 30, 10, 54, 34, 181, 161, 157, 137, 229, 241, 205, 217, 21, 1, 61, 41, 69, 81, 109, 121, 88, 76, 112, 100, 8, 28, 32, 52, 248, 236, 208, 196, 168, 188, 128, 148, 3, 23, 43, 63, 83, 71, 123, 111, 163, 183, 139, 159, 243, 231, 219, 207, 153, 141, 177, 165, 201, 221, 225, 245, 57, 45, 17, 5, 105, 125, 65, 85, 194, 214, 234, 254, 146, 134, 186, 174, 98, 118, 74, 94, 50, 38, 26, 14, 47, 59, 7, 19, 127, 107, 87, 67, 143, 155, 167, 179, 223, 203, 247, 227, 116, 96, 92, 72, 36, 48, 12, 24, 212, 192, 252, 232, 132, 144, 172, 184},
                    {0, 21, 42, 63, 84, 65, 126, 107, 168, 189, 130, 151, 252, 233, 214, 195, 75, 94, 97, 116, 31, 10, 53, 32, 227, 246, 201, 220, 183, 162, 157, 136, 150, 131, 188, 169, 194, 215, 232, 253, 62, 43, 20, 1, 106, 127, 64, 85, 221, 200, 247, 226, 137, 156, 163, 182, 117, 96, 95, 74, 33, 52, 11, 30, 55, 34, 29, 8, 99, 118, 73, 92, 159, 138, 181, 160, 203, 222, 225, 244, 124, 105, 86, 67, 40, 61, 2, 23, 212, 193, 254, 235, 128, 149, 170, 191, 161, 180, 139, 158, 245, 224, 223, 202, 9, 28, 35, 54, 93, 72, 119, 98, 234, 255, 192, 213, 190, 171, 148, 129, 66, 87, 104, 125, 22, 3, 60, 41, 110, 123, 68, 81, 58, 47, 16, 5, 198, 211, 236, 249, 146, 135, 184, 173, 37, 48, 15, 26, 113, 100, 91, 78, 141, 152, 167, 178, 217, 204, 243, 230, 248, 237, 210, 199, 172, 185, 134, 147, 80, 69, 122, 111, 4, 17, 46, 59, 179, 166, 153, 140, 231, 242, 205, 216, 27, 14, 49, 36, 79, 90, 101, 112, 89, 76, 115, 102, 13, 24, 39, 50, 241, 228, 219, 206, 165, 176, 143, 154, 18, 7, 56, 45, 70, 83, 108, 121, 186, 175, 144, 133, 238, 251, 196, 209, 207, 218, 229, 240, 155, 142, 177, 164, 103, 114, 77, 88, 51, 38, 25, 12, 132, 145, 174, 187, 208, 197, 250, 239, 44, 57, 6, 19, 120, 109, 82, 71}
            };
}
