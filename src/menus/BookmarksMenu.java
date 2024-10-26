package menus;

import models.Bookmarks;

import java.util.Scanner;

public class BookmarksMenu {

    public void show_menu() {
        Bookmarks bookmarks=new Bookmarks("bookmarks.json");

        int page=0;
        int pages= (int) Math.ceil(bookmarks.size()/5.0);
        String user_input;


        while(true) {
            int max_value=page!=pages-1?5:bookmarks.size()-(5*(page));
            Scanner scanner = new Scanner(System.in);
            if(pages==0){
                System.out.println("====================================================================");
                System.out.println("There's no bookmarks added yet/[R]eturn");
                System.out.println("====================================================================");
                System.out.print(":>");
                user_input= scanner.nextLine();
                if(user_input.equalsIgnoreCase("r"))break;
                else{
                    System.out.println("Invalid selection.");

                    continue;
                }
            }
            System.out.println("====================================================================");
            System.out.println("[N]ext page/[P]revious page/[R]eturn:");
            System.out.println("Page "+(page+1)+"/"+pages);
            System.out.println("====================================================================");
            System.out.println(max_value);
            for (int i = 0; i < max_value; i++)
                System.out.println("[" + (i + 1) + "] " + bookmarks.get_bookmark((page*5) + i).toString());
            System.out.print(":>");
            user_input = scanner.nextLine();
            if (user_input.equalsIgnoreCase("r")) break;
            else if(user_input.equalsIgnoreCase("n")){
                page++;
                if(page==pages)page=0;
            }
            else if(user_input.equalsIgnoreCase("p")){
                page--;
                if(page<0)page=pages-1;
            }
            else{
                try{
                    int user_selection=Integer.parseInt(user_input);
                    if(user_selection>max_value || user_selection<0)
                        System.out.println("Invalid selection.");
                }catch (NumberFormatException e){
                    System.out.println("Invalid selection.");
                }
            }
        }
    }
}
