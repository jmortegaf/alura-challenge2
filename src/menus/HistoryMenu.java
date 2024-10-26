package menus;

import models.History;

import java.util.Scanner;

public class HistoryMenu {

    public void  show_menu(){
        History history=new History("history.json");
        int page=0;
        int pages=(int)Math.ceil(history.size()/5.0);

        while (true){
            int max_value=page!=pages-1?5:history.size()-(5*(page));
            System.out.println("====================================================================");
            System.out.println("Exchange History");
            System.out.println("Page "+(page+1)+"/"+pages);
            System.out.println("[N]ext page/[P]revious page/[R]eturn");
            System.out.println("====================================================================");
            for (int i = 0; i < max_value; i++)
                System.out.println(history.get_exchange((page*5) + i).toString());
            System.out.print(":>");
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            if (user_input.equalsIgnoreCase("r")) break;
            else if(user_input.equalsIgnoreCase("n")){
                page++;
                if(page==pages)page=0;
            }
            else if(user_input.equalsIgnoreCase("p")){
                page--;
                if(page<0)page=pages-1;
            }
            else System.out.println("Invalid selection.");

        }



    }
}
