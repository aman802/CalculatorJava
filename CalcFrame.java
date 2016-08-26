import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class CalcFrame extends JFrame
{
	private JTextArea display;
	private ActionListener nListener;
	private ActionListener dListener;
	private ActionListener aListener;
	private ActionListener bListener;
	private ActionListener pListener;
	private ActionListener rListener;
	private ActionListener sListener;
	private ActionListener eListener;

	int dotCount, symbol;
	Double fNum, sNum;
	boolean pm; //for + --> true, for -  --> false;
	boolean sym; //true for 1 symbol(+,-,*/), false when 0 symbol present

	public CalcFrame()
	{
		dotCount = 0;
		symbol = 0;
		fNum = null;	sNum = null;
		pm = true;
		sym = false;
		display = createDisplay(12);		//12 is the number of digits displayed
		setLayout(new GridLayout(2,1));
		add(display);
		
		class numberListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				display.append(((JButton) event.getSource()).getActionCommand());
			}
		}

		class dotListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(dotCount==0)
				{
					dotCount++;
					display.append(((JButton) event.getSource()).getActionCommand());
				}
				else
					display.setText("ERROR. Click AC");
			}
		}
		
		class ACListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				dotCount = 0;
				symbol = 0;
				pm = true;
				sym = false;
				fNum = null;
				sNum = null;
			}
		}

		class backListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String text = display.getText();
				display.setText(text.substring(0,text.length()-1));
			}
		}

		class PMListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				pm = !pm;
				if(pm)
					display.replaceRange("",0,1);
				else
					display.insert("-",0);
			}
		}

		class rootListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				fNum = Double.parseDouble(display.getText());
				double root = java.lang.Math.sqrt(fNum);
				display.setText("" + root);
			}
		}

		class symbolListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				fNum = Double.parseDouble(display.getText());
				if(!sym)
				{
					switch(((JButton) event.getSource()).getActionCommand())
					{
						case "+": symbol = 1;
							break;
						case "-": symbol = 2;
							break;
						case "X": symbol = 3;
							break;
						case "/": symbol = 4;
							break;
					}
					sym = true;
					dotCount = 0;
					System.out.println("Symbol Boolean: " + sym);
					System.out.println("Symbol Integer: " + symbol);
					display.setText("");
				}
			}
		}

		class equalListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				Double ans;
				sNum = Double.parseDouble(display.getText());
				System.out.println("Sum of fNum and sNum: " + fNum + sNum);
				if(sym)
				{
				switch(symbol)
				{
					case 0: display.setText("" + sNum);
						break;
					case 1: ans = (fNum + sNum);
						System.out.println("Answer: " + ans);
						display.setText("" + ans);
						fNum = ans;
						break;
					case 2: ans = (fNum - sNum);
						System.out.println("Answer: " + ans);
						display.setText("" + ans);
						fNum = ans;
						sNum = null;
						break;
					case 3: ans = (fNum * sNum);
						System.out.println("Answer: " + ans);
						display.setText("" + ans);
						fNum = ans;
						sNum = null;
						break;
					case 4: ans = (fNum / sNum);
						System.out.println("Answer: " + ans);
						display.setText("" + ans);
						fNum = ans;
						sNum = null;
						break;
				}
				}
		
				dotCount = 0;
				symbol = 0;
				pm = true;
				sym = false;
			}
		}

		nListener = new numberListener();
		dListener = new dotListener();
		aListener = new ACListener();
		bListener = new backListener();
		pListener = new PMListener();
		rListener = new rootListener();
		sListener = new symbolListener();
		eListener = new equalListener();

		add(createCalcButtons());
		setSize(500,500);
		setTitle("Calculator by Aman Vangani");
	}

	private JTextArea createDisplay(int n)
	{
		JTextArea ta = new JTextArea(1,n);
		ta.setFont(new Font("Arial", Font.PLAIN, 30));
		ta.setText("");
		ta.setEditable(false);
		return ta;
	}

	public JPanel createCalcButtons()
	{
		JButton button0 = new JButton("0");
		JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");
		JButton button4 = new JButton("4");
		JButton button5 = new JButton("5");
		JButton button6 = new JButton("6");
		JButton button7 = new JButton("7");
		JButton button8 = new JButton("8");
		JButton button9 = new JButton("9");
		JButton buttonEqual = new JButton("=");
		JButton buttonPlus = new JButton("+");
		JButton buttonMinus = new JButton("-");
		JButton buttonMultiply = new JButton("X");
		JButton buttonDivide = new JButton("/");
		JButton buttonDot = new JButton(".");
		JButton buttonAC = new JButton("AC");
		JButton buttonBack = new JButton("<=");
		JButton buttonRoot = new JButton("sqrt(x)");
		JButton buttonPlusMinus = new JButton("+/-");

		buttonAC.setBackground(Color.RED);
		buttonAC.setForeground(Color.BLACK);
		
		button0.addActionListener(nListener);
		button1.addActionListener(nListener);
		button2.addActionListener(nListener);
		button3.addActionListener(nListener);
		button4.addActionListener(nListener);
		button5.addActionListener(nListener);
		button6.addActionListener(nListener);
		button7.addActionListener(nListener);
		button8.addActionListener(nListener);
		button9.addActionListener(nListener);

		buttonPlus.addActionListener(sListener);
		buttonMinus.addActionListener(sListener);
		buttonMultiply.addActionListener(sListener);
		buttonDivide.addActionListener(sListener);

		buttonDot.addActionListener(dListener);
		buttonAC.addActionListener(aListener);
		buttonBack.addActionListener(bListener);
		buttonPlusMinus.addActionListener(pListener);
		buttonRoot.addActionListener(rListener);
		buttonEqual.addActionListener(eListener);

		button0.setFont(new Font("Arial", Font.PLAIN, 25));
		button1.setFont(new Font("Arial", Font.PLAIN, 25));
		button2.setFont(new Font("Arial", Font.PLAIN, 25));
		button3.setFont(new Font("Arial", Font.PLAIN, 25));
		button4.setFont(new Font("Arial", Font.PLAIN, 25));
		button5.setFont(new Font("Arial", Font.PLAIN, 25));
		button6.setFont(new Font("Arial", Font.PLAIN, 25));
		button7.setFont(new Font("Arial", Font.PLAIN, 25));
		button8.setFont(new Font("Arial", Font.PLAIN, 25));
		button9.setFont(new Font("Arial", Font.PLAIN, 25));
		buttonPlus.setFont(new Font("Arial", Font.PLAIN, 25));
		buttonMinus.setFont(new Font("Arial", Font.PLAIN, 30));
		buttonMultiply.setFont(new Font("Arial", Font.PLAIN, 25));
		buttonDivide.setFont(new Font("Arial", Font.PLAIN, 30));
		buttonEqual.setFont(new Font("Arial", Font.PLAIN, 25));
		buttonDot.setFont(new Font("Arial", Font.PLAIN, 35));
		buttonAC.setFont(new Font("Arial", Font.PLAIN, 25));
		buttonRoot.setFont(new Font("Arial", Font.PLAIN, 20));
		buttonPlusMinus.setFont(new Font("Arial", Font.PLAIN, 25));
		buttonBack.setFont(new Font("Arial", Font.PLAIN, 25));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,5));
		panel.add(button7);
		panel.add(button8);
		panel.add(button9);
		panel.add(buttonPlus);
		panel.add(buttonBack);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(buttonMinus);
		panel.add(buttonAC);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(buttonMultiply);
		panel.add(buttonRoot);
		panel.add(button0);
		panel.add(buttonDot);
		panel.add(buttonEqual);
		panel.add(buttonDivide);
		panel.add(buttonPlusMinus);
		return panel;
	}
}
