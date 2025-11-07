1. Đọc file - BufferedReader 
    cách gọi: import java.io BufferedReader;        // dùng để đọc văn bản từ file
              import java.io.FileReader;        // dùng để mở file văn bản để dộc từng kí tự
              import java.io.IOException;       // ngoại lệ xảy ra khi có lỗi đọc file 

        try (BufferedReader reader = new BufferedReader(new FileReader("pets.txt"))){      // tự động đóng sau khi đọc xong, đọc theo dòng
            String line; 
            while( (line = reader.readLine()) != null ){    // nếu dòng đang đọc có tồn tại thì nó sẽ đọc từng dòng
                System.out.println(line);
            }
        } catch (IOExecption e){        // nếu file không tồn tại thì nó sẽ thực hiện gì đó
            e.printStackTrace();    // in thông tin lỗi
            System.out.println();   // thông báo cho người dùng
        }


2. Chỉnh sửa file - BufferedWriter

    Sửa dựa theo id của pet

    BufferedWriter writer = new BufferedWriter(new FileWriter("pets.txt"));





