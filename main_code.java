package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OOPH3 {
	Scanner scan = new Scanner(System.in);
	
	// Menu Variables
	String username;
	String password;
	Integer menu;
	
	// Game Variables
	String operator;
	String type;
	Integer hp, att, dp, level = 1;
	Integer lmd = 500;
	Integer trust = 1;
	Integer maxTrust = 3;
	Integer gameOption;
	String healOption;
	String talkOption;
	String battleOption;
	String promoteOption;
	
	public OOPH3() {
		readFile();
		do{
			do{
				mainMenu();
				menu = scan.nextInt();
			}while(menu < 1 || menu > 3);
			switch (menu) {
			case 1:
				scan.nextLine();
				do{
					System.out.print("Username [Min 5] : ");
					username = scan.nextLine();
				}while(username.length() < 5);
				
				do {
					System.out.print("Password [Min 5] : ");
					password = scan.nextLine();
				}while(password.length() < 5);
				
				if(isCredentialsValid(username, password)) {
					System.out.println("Welcome back Dokutah " + username + "!");
					System.out.println("Press [ENTER] to continue...");
					scan.nextLine();
					
					// Game
					System.out.println("=====================================================");
					System.out.println("|            Please choose your Operator            |");
					System.out.println("-----------------------------------------------------");
					System.out.println("|  Name        |  HP   |  Attack  |   Type   |  DP  |");
					System.out.println("-----------------------------------------------------");
					System.out.println("|  Hellagur    |  100  |    20    |  Melee   |  20  |");
					System.out.println("|  Eyjafjalla  |  50   |    40    |  Ranged  |  20  |");
					System.out.println("|  Texas       |  70   |    30    |  Hybrid  |  15  |");
					System.out.println("=====================================================");
					System.out.print("Input the name of your chosen operator [Case Sensitive]: ");
					operator = scan.nextLine();
					
					if(!(operator.equals("Hellagur") || operator.equals("Eyjafjalla") || operator.equals("Texas"))) {
						do{
							System.out.print("Please choose one operator [Case Sensitive]: ");
							operator = scan.nextLine();
						}while(!(operator.equals("Hellagur") || operator.equals("Eyjafjalla") || operator.equals("Texas")));
					}
					
					if(operator.equals("Hellagur") || operator.equals("Eyjafjalla") || operator.equals("Texas")) {
						do{
							do{
								System.out.printf("Current LMD : %d          Trust Level : %d\n", lmd, trust);
								System.out.println("==========================================");
								System.out.printf("|            Welcome, Dr.%-3s           |\n", username);
								System.out.println("------------------------------------------");
								
								if(operator.equals("Hellagur")) {
									hp = 100;
									att = 20;
									type = "Melee";
									dp = 20;
									
									System.out.printf("|           Partner : %-1s          |\n", operator);
									System.out.println("------------------------------------------");
									System.out.printf("|  Archetype :  %-3s | DP Cost : %-1d      |\n", type, dp);
									System.out.printf("|  HP        :  %-5d | Attack  : %-1d      |\n", hp, att);
									
								} else if(operator.equals("Eyjafjalla")) {
									hp = 50;
									att = 40;
									type = "Ranged";
									dp = 20;
									
									System.out.printf("|           Partner : %-1s          |\n", operator);
									System.out.println("------------------------------------------");
									System.out.printf("|  Archetype :  %-3s | DP Cost : %-1d      |\n", type, dp);
									System.out.printf("|  HP        :  %-5d | Attack  : %-1d      |\n", hp, att);
									
								} else if(operator.equals("Texas")) {
									hp = 70;
									att = 30;
									type = "Hybrid";
									dp = 15;
									
									System.out.printf("|           Partner : %-1s         |\n", operator);
									System.out.println("------------------------------------------");
									System.out.printf("|  Archetype :  %-3s | DP Cost : %d       |\n", type, dp);
									System.out.printf("|  HP        :  %-5d | Attack  : %d       |\n", hp, att);
						
								}
								
								System.out.println("==========================================");
								System.out.println("");
								gameMenu();
								gameOption = scan.nextInt();
							}while(gameOption < 1 || gameOption > 5);
							
							switch (gameOption) {
							case 1:
								scan.nextLine();
								System.out.println("One mistake and it's all over, are you sure to continue? [Y/N] [Case Insensitive]");
								System.out.print(">> ");
								battleOption = scan.nextLine();
								System.out.print("Press [ENTER] to continue...");
								scan.nextLine();
								break;
							case 2:
								scan.nextLine();
								System.out.printf("Current promotion level: %d\n", level);
								System.out.print("Press [ENTER] to continue...");
								scan.nextLine();
								
								System.out.printf("Promotion to level %d Cost : 1000 LMD\n", level + 1);
								System.out.print("Press [ENTER] to continue...");
								scan.nextLine();
								
								System.out.printf("Do you want to promote %s? [Y/N] [Case Sensitive]", operator);
								do{
									System.out.print(">> ");
									promoteOption = scan.nextLine();
								}while(!(promoteOption.equalsIgnoreCase("Y")) && !(promoteOption.equalsIgnoreCase("N")));
								
								if(promoteOption.equalsIgnoreCase("Y")) {
									if(lmd < 1000) {
										System.out.println("Insufficient LMD for promotion.");
										System.out.print("Press [ENTER] to continue...");
										scan.nextLine();
									} else {
										level += 1;
										System.out.printf("%s has been promoted to level %d!", operator, level);
										System.out.print("Press [ENTER] to continue...");
										scan.nextLine();
									}
								} else {
									System.out.print("Press [ENTER] to continue...");
									scan.nextLine();
								}
								break;
							case 3:
								scan.nextLine();
								System.out.println("Do you want to heal your operator? It will cost you... [-100LMD] [Y/N] [Case Insensitive]");
								do{
									System.out.print(">> ");
									healOption = scan.nextLine();
								}while(!(healOption.equalsIgnoreCase("Y")) && !(healOption.equalsIgnoreCase("N")));
								
								if(healOption.equalsIgnoreCase("Y")) {
									if(lmd < 100) {
										System.out.println("You don't have enough LMD!");
										System.out.print("Press [ENTER] to continue...");
										scan.nextLine();
									} else if(lmd > 100) {
										hp += 100;
										lmd -= 100;
										System.out.printf("Healed! Current HP: %d\n", hp);
										System.out.print("Press [ENTER] to continue...");
										scan.nextLine();
									}
								} else if(healOption.equalsIgnoreCase("N")) {
									System.out.print("Press [ENTER] to continue...");
									scan.nextLine();
								}
								
								break;
							case 4:
								scan.nextLine();
								if(trust.equals(1)) {
									System.out.printf("%s looks sad. Do you want to talk with your partner? [Y/N] [Case Insensitive]\n", operator);
								} else if(trust.equals(2)) {
									System.out.printf("%s looks hesitant. Do you want to talk with your partner? [Y/N] [Case Insensitive]\\n", operator);
								} else if(trust.equals(3)) {
									System.out.printf("%s appears determined and shares their thought with you enthusiastically. Do you want to talk with your partner? [Y/N] [Case Insensitive]\n", operator);
								}
								do{
									System.out.print(">> ");
									talkOption = scan.nextLine();
								}while(!(talkOption.equalsIgnoreCase("Y")) && !(talkOption.equalsIgnoreCase("N")));
								
								if(talkOption.equalsIgnoreCase("Y")) {
									if(trust < maxTrust) {
										trust += 1;
										System.out.printf("Trust Level increased to: %d\n", trust);
										System.out.print("Press [ENTER] to continue...");
										scan.nextLine();
									} else if(trust == maxTrust) { 
										System.out.println("Maximum trust level has been reached");
										System.out.print("Press [ENTER] to continue...");
										scan.nextLine();
									}
								} else if(talkOption.equalsIgnoreCase("N")) {
									System.out.print("Press [ENTER] to continue...");
									scan.nextLine();
								}
								
								break;
							case 5:
								System.out.printf("Farewell, Dr. %s...\n", username);
								scan.nextLine();
								System.out.print("Press [ENTER] to continue...");
								scan.nextLine();
								break;
							}
						}while(gameOption != 5);
;					}
					break;
				} else {
					System.out.println("Login failed. Incorrect credentials or the account does not exist.");
					System.out.println("Press [ENTER] to continue...");
					scan.nextLine();
				}
				break;
			case 2:
				scan.nextLine();
				System.out.println("Please tell us your name, Dokutah... ['0' to back]");
				System.out.print(">> ");
				username = scan.nextLine();
				
				if(username.equals("0")) {
					break;
				}
				
				if(username.length() < 5) {
					do {
						System.out.println("Dokutah... your name is not that short... [Min 5]");
						System.out.print(">> ");
						username = scan.nextLine();
					}while(username.length() < 5);
				}
				
				if(isUsernameTaken(username)) {
					do {
						System.out.println("Dokutah... this name has already been used. Please choose another one.");
						System.out.print(">> ");
						username = scan.nextLine();
					}while(isUsernameTaken(username));
				}
					
				System.out.println("Now, create a strong password to protect your identity, Dokutah... ['0' to back]");
				System.out.print(">> ");
				password = scan.nextLine();
				
				if(password.equals("0")) {
					break;
				}
				
				if(!isAlphanumeric(password)) {
					do {
						System.out.println("Dokutah... your password should be alphanumeric...");
						System.out.print(">> ");
						password = scan.nextLine();
					}while(!isAlphanumeric(password));
				}
				
				writeFile(username, password);
				
				System.out.println("Identity has been successfully recorded, Welcome back to Terra, Dokutah");
				System.out.print("Press [ENTER] to continue...");
				scan.nextLine();
				break;
			case 3:
				exitMenu();
				break;
			}
		}while(!(menu.equals(3)));
	}
	
	public void mainMenu() {
		System.out.println(" _______  _______  _        _       _________ _______          _________ _______ ");
        System.out.println("(  ___  )(  ____ )| \\    /\\( (    /|\\__   __/(  ____ |\\     /|\\__   __/(  ___  )");
        System.out.println("| (   ) || (    )||  \\  / /|  \\  ( |   ) (   | (    \\/| )   ( |   ) (   | (   ) |");
        System.out.println("| (___) || (____)||  (_/ / |   \\ | |   | |   | |      | (___) |   | |   | |   | |");
        System.out.println("|  ___  ||     __)|   _ (  | (\\ \\) |   | |   | | ____ |  ___  |   | |   | |   | |");
        System.out.println("| (   ) || (    |  | ( \\ \\ | | \\   |   | |   | | \\_  )| (   ) |   | |   | |   | |");
        System.out.println("| )   ( || ) \\   |  | ) \\   | )  \\  |___) (___| (___) || )   ( |   | |   | (___) |");
        System.out.println("|/     \\||/   \\__|_/   \\/|/    )_)\\_______/(_______)|/     \\|   )_(   (_______)");
        System.out.println("");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print(">> ");
	}
	
	public void exitMenu() {
		System.out.println("                              Thank you for using the application!");
        System.out.println("                             .:^:.. ..^^:..");
        System.out.println("                          ...   ...:.     .......    .");
        System.out.println("                            .   . ^.   :..:~~77~~~::..::.. ..");
        System.out.println("                          ::::..~..:..:~7~.: :^ ~^:~..:....:^~^:^~^");
        System.out.println("                        ..      .. .^!777^~~!?~^~7:^::.  .5BGB##&#7.");
        System.out.println("                             .:~!!. .:~^.77?P57P7!^::~::?5B&&#GGG5.");
        System.out.println("                          .:^~~^^: YJJGBGP5YY7J7!^^^?7YG##P7^7#&B~:");
        System.out.println("                        .^~~:  ~J55PJ~:.       ..~JPBBBY^. .:.JG!:!!^..");
        System.out.println("                     ..^7~  .::???.         .~!JG&&#J:       .7!:.^77?!^..");
        System.out.println("                   .:~7!:. ^Y^.  ..      .!JPB&&#Y:          .  :7!: ~G5?!:.");
        System.out.println("                 .:~7?7..~!!       .:..^?YP&&&G7         .        ~J: .BP7J!.");
        System.out.println("               .:^777~^::7^          :JB&&#BG^ .                   .J! !YYYJ?^.");
        System.out.println("              .:!JJJJ: :7G.         ?#&&&&B~                         ?5..^!~~!!:.");
        System.out.println("             .:!YYY5~ .J&Y      . .5BBBBBG^                           YP  :?J!~!^.");
        System.out.println("             .:!J?YJ^..P&!        P&&&&&#!             ..              #7 .^55?^~:");
        System.out.println("              .^7!??^..P#? .  .   ~#&&&&&J:..   ...  .   ..  ...     . 5#..:J57~!.");
        System.out.println("              .:!J~~^.?G#5      .:.^JGBBBP5Y!^75GBGP5PJYPGPP5YYJ?!^    !&:.:??~?~:.");
        System.out.println("               .^?5^:.JPBP!       .:::.. .!YYY&&&###&&&&YG##&&&&#B5:   !&:.^~!5J~.");
        System.out.println("                .^??:.^^?77..                 ^:.  ..P&G:^&&&&&&&&7    PG ~7:JP7:.");
        System.out.println("                .:^7? .~~J?7!                 .      7#^ P&&&&&&G^    :&^ 7?5?!:.");
        System.out.println("                  .^J5.:!JPBBY            .:.       ^5^ 7###&#P^      G7..7G5!:.");
        System.out.println("                   .^?5~^~:~PBP~.?!~: ^^7~^!^     .7: ~#&&#5~        ~~:.:PY~:.");
        System.out.println("                    .^!5J7..~!B&PJY?J^J?YGY?7!:..^^:?#@&G7.        .~.::^Y!^.");
        System.out.println("                     .:^??J!77JY57 .: :?#BYGG!7~:7G&#5~.           .~^!J5!:.");
        System.out.println("                       .:~?JY~..... :.^!J^^!7~7PGPJ~.          ... ^?Y57:.");
        System.out.println("                          :~75P~:.  ^^!!^~!JYJ57:..^^....:^!:  ...5GP7^.");
        System.out.println("                          ::.:~^7.JY^?77Y5Y7^..::~??5J???5J: :?Y!J5!:.");
        System.out.println("                           .  .^.7G&###B5!^:.:.::......~?J?YB#BY7^:.");
        System.out.println("                              .^^YJ5###BG5J7!7JYPPGYJ??YPPPY?7~:.");
        System.out.println("                              ..:^~^^........^~!~!!!!~^^::...");
        System.out.println("                       o---------------------------------------------o");
        System.out.println("                       |      Breaking and Overcoming Challenges     |");
        System.out.println("                       |    Through Courage Hardwork and Persistence |");
        System.out.println("                       o---------------------------------------------o");
        System.out.println("");
	}
	
	public void gameMenu() {
		System.out.println("--- Main Menu ---");
		System.out.println("1. Go to battle");
		System.out.println("2. Promote Operator");
		System.out.println("3. Heal Operator [100HP for 100 LMD]");
		System.out.println("4. Talk to Operator");
		System.out.println("5. Save and Quit Game");
		System.out.print("Enter your choice: ");
	}
	
	public void systemPause() {
		scan.nextLine();
		System.out.print("Press any key to back to the main menu...");
		scan.nextLine();
	}
	
	public static boolean isAlphanumeric(String str) {
        return str.matches("^[a-zA-Z0-9]+$");
    }
	
	public boolean isUsernameTaken(String username) {
	    try {
	        File file = new File("Doctor_Memento.txt");
	        Scanner scanner = new Scanner(file);

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split("#");

	            if (parts.length >= 1 && parts[0].equals(username)) {
	                scanner.close();
	                return true; 
	            }
	        }
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public void readFile() {
		File mazeFile = new File("Doctor_Memento.txt");
		try {
			Scanner reader = new Scanner(mazeFile);
			while(reader.hasNextLine()) {
				String line = reader.nextLine().trim();
	            System.out.println(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFile(String name, String pass) {
		try {
			FileWriter fw = new FileWriter("Doctor_Memento.txt", true);
			fw.append("\n" + name + "#" + pass + "#500#1#-");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isCredentialsValid(String username, String password) {
	    try {
	        File file = new File("Doctor_Memento.txt");
	        Scanner scanner = new Scanner(file);

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split("#");

	            if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
	                scanner.close();
	                return true; 
	            }
	        }
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return false; 
	}

	
	public static void main(String[] args) {
		new OOPH3();
	}

}
