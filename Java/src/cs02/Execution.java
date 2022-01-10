package cs02;

import java.util.Scanner;

import static cs02.Editor.*;

public class Execution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VideoData.main();
        String str = " ";
        String[] fullCommand;
        String commandId;
        while(!(str.equals("Q")) && !(str.equals("q"))){
            System.out.println("명령어를 입력하세요 (끝내려면 Q 또는 q를 입력하세요)");
            System.out.print(">");
            str = sc.nextLine();
            fullCommand = str.split(" ");
            System.out.println(str);
            String command = fullCommand[0];
            if (command.equals("render")){
                render();
                continue;
            }


            if (command.equals("insert")){
                commandId = fullCommand[1];
                if (fullCommand.length < 3){
                    System.out.println("명령어를 다음과 같은 형식으로 입력해주세요 : insert id location");
                    continue;
                }
                int commandLocation = Integer.parseInt(fullCommand[2]);
                insert(commandId, commandLocation);
                continue;
            }

            if (command.equals("add")){
                commandId = fullCommand[1];
                add(commandId);
                continue;
            }

            if (command.equals("delete")){
                commandId = fullCommand[1];
                delete(commandId);
                continue;
            }
        }

    }

}
