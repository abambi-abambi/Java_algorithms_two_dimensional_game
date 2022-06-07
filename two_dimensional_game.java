import java.util.Scanner;

public class two_dimensional_game {
	public static void main(String[] argv) {
		Scanner			in = new Scanner(System.in);
		int				sets = in.nextInt();
		StringBuilder	str = new StringBuilder("");

		for (int i = 0; i < sets; i++) {
			int		height = in.nextInt();
			int		width  = in.nextInt();
			str.delete(0, str.length());
			for (int j = 0; j <= height; j++) {
				str.append(in.nextLine());
			}
			calculator(str, width, height);
		}
		in.close();
	}
	
	public static void calculator(StringBuilder str, int width, int repetitions) {
		int				counter;
		int				target_point;
		int				max;
		int				index_max;
		int[]			arrayInt = new int[width];

		for (int j = 0; j < width; j++) {
			counter = 0;
			for (int i = 0; i < repetitions; i++) {
				target_point = j + i * width;
				if (str.charAt(target_point) == '*') {
					counter++;
				}
			}
			arrayInt[j] = counter;
		}
		newGameMap(arrayInt, width, repetitions);
	}

	public static int findIndexOfMax(int[] arrayInt, int i, int width) {
		int max = 0;
		int	index = i;
		
		for ( ; i < width; i++) {
			if (arrayInt[i] > max) {
				max = arrayInt[i];
				index = i;
			}
		}
		return (index);
	}

	public static void newGameMap(int[] arrayInt, int width, int height) {
		StringBuilder	gameMap = new StringBuilder("");
		int				local_max = 0;
		int				fisrt_max;
		int				next_max;
		int				first_max_index;
		int				next_max_index;
		char			sign;

		first_max_index = findIndexOfMax(arrayInt, 0, width);
		fisrt_max = arrayInt[first_max_index];

		for (int i = 0; i < height; i++) {
			local_max = 0;
			
			for (int j = 0; j < width; j++) {
				if (arrayInt[j] > local_max )
					local_max = arrayInt[j];
				if (i < height - arrayInt[j]) {
					if (j <= first_max_index) {
						if (i < height - local_max)
							sign = '.';
						else
							sign = '~';
					}
					else {
						next_max_index = findIndexOfMax(arrayInt, j, width);
						next_max = arrayInt[next_max_index];
						if ((j <= next_max_index || j == width - 1) &&
							i < height - next_max)
							sign = '.';
						else
							sign = '~';
					}
				} else {
					sign = '*';
				}
				gameMap.append(sign);
			}
			gameMap.append('\n');
		}
		printMap(gameMap);
	}

	public static void printMap(StringBuilder gameMap) {
		System.out.print(gameMap);
		System.out.print("\n");
	}
}
