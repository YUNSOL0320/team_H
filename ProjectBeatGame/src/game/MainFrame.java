package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private final int SCREEN_WIDTH = 1280;
	private final int SCREEN_HEIGHT = 720;
	
	public static final int NOTE_SPEED = 3;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 2;
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introScreen.png")).getImage();
	private Image introScreen = new ImageIcon(Main.class.getResource("../images/introScreen.png")).getImage();
	private Image mainScreen = new ImageIcon(Main.class.getResource("../images/mainScreen.png")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	private Image selectedImage;
	private Image firstSong_Sample = new ImageIcon(Main.class.getResource("../images/LastChristmas_Sample.jpg")).getImage();
	private Image secondSong_Sample = new ImageIcon(Main.class.getResource("../images/MarryAndHappy_Sample.png")).getImage();
	private Image thirdSong_Sample = new ImageIcon(Main.class.getResource("../images/LoveMe_Sample.jpg")).getImage();
	private Image firstSong_Main = new ImageIcon(Main.class.getResource("../images/LastChristmas_Main.jpg")).getImage();
	private Image secondSong_Main = new ImageIcon(Main.class.getResource("../images/MarryAndHappy_Main.png")).getImage();
	private Image thirdSong_Main = new ImageIcon(Main.class.getResource("../images/LoveMe_Main.jpg")).getImage();

	private int nowSelected = 0;
	
	private IntroMusic introMusic;
	private FirstMusic firstMusic;
	private SecondMusic secondMusic;
	private ThirdMusic thirdMusic;
	private FirstSampleMusic firstSampleMusic;
	private SecondSampleMusic secondSampleMusic;
	private ThirdSampleMusic thirdSampleMusic;
	
	public static int index = 0;	//노래 선택 인덱스
	
	public static Play play;
	
	public int score;
	
	public MainFrame() {
		

		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusic = new IntroMusic(true);	//인트로 음악
		firstMusic = new FirstMusic(true);	//게임 플레이 첫번째 음악
		secondMusic = new SecondMusic(true);	//게임 플레이 두번째 음악
		thirdMusic = new ThirdMusic(true);	//게임 플레이 세번째 음악
		firstSampleMusic = new FirstSampleMusic(true);
		secondSampleMusic = new SecondSampleMusic(true);
		thirdSampleMusic = new ThirdSampleMusic(true);
		
		selectedImage = firstSong_Sample;
		
		Timer timer = new Timer();		//노래 시간에 따라 비트들이 내려올 시간을 체크할 타이머
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				
				Play.nowTime = System.currentTimeMillis();
				Play.checkTime = Play.nowTime - Play.startTime;
				
				if(Play.startTime != 0) {
					
					//System.out.println(Game.checkTime);
				}
				
			}
			
		}, 0 ,1);
		
		introMusic.start();
		
		//시작버튼
		startButton.setBounds(380, 530, 230, 82);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				enterMain();
			}
		});
		add(startButton);
		
		// 나가기 버튼
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {	
				try {
					Thread.sleep(200);
				} 
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		// QUIT BUTTON
		quitButton.setBounds(690, 530, 230, 82);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		
		// 노래선택 왼쪽으로 버튼
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(index == 0) {
					index = 2;
					selectedImage = thirdSong_Sample;
				}
				else if(index == 2) {
					index = 1;
					selectedImage = secondSong_Sample;
				}
				else{
					index = 0;
					selectedImage = firstSong_Sample;
				}
				playSampleMusic(index);
				
			}
		});
		
		add(leftButton);
		
		// 노래선택 오른쪽으로 버튼
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(index == 0) {
					index = 1;
					selectedImage = secondSong_Sample;
				}
				else if(index == 1) {
					index = 2;
					selectedImage = thirdSong_Sample;
				}
				else {
					index = 0;
					selectedImage = firstSong_Sample;
				}
				playSampleMusic(index);
			}
		});
		
		add(rightButton);
		
		// 난이도 선택 및 게임시작 버튼
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
		// 난이도 선택 및 게임시작 버튼
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
		// 뒤로가기 버튼
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				backMain();
				playSampleMusic(index);
			}
		});
		add(backButton);

		// 메뉴바
		menuBar.setBounds(0,0,1280,30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-mouseX, y-mouseY);
			}
		});
		add(menuBar);
		
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		Play.nowTime = System.currentTimeMillis();
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) {									//메인화면에서의 화면 출력
			g.drawImage(selectedImage, 340, 100, null);
		}
		if(isGameScreen) {									//게임 시작시 화면 출력
			play.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	
	
	public void gameStart(int nowSelected, String difficulty) {		//게임 시작 전 설정
		isMainScreen = false;
		isGameScreen = true;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);
		score = 0;
		if(index == 0) {
			background = firstSong_Main;
		}
		else if(index == 1) {
			background = secondSong_Main;
		}
		else if(index == 2){
			background = thirdSong_Main;
		}
		play = new Play(index, difficulty);
		play.start();
		setFocusable(true);
		Play.startTime = System.currentTimeMillis();
		playMusic(index);
	}
	
	public void backMain() {									//뒤로가기 버튼 누를 시 설정
		isGameScreen = false;
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = mainScreen;
		playSampleMusic(index);
		backButton.setVisible(false);
		play.close();
	}
	
	public void enterMain() {									//메인 화면 들어갈 시 설정
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = mainScreen;
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		playSampleMusic(index);
	}
	
	public void playMusic(int index) {						//노래 선택 클래스
		introMusic.stop();
		firstMusic.stop();
		secondMusic.stop();
		thirdMusic.stop();
		firstSampleMusic.stop();
		secondSampleMusic.stop();
		thirdSampleMusic.stop();
		
		if(index == 0) {
			firstMusic.start();
		}
		else if(index == 1) {
			secondMusic.start();
		}
		else {
			thirdMusic.start();
		}
		
		
	}
	
	public void playSampleMusic(int index) {						//노래 선택 클래스
		introMusic.stop();
		firstMusic.stop();
		secondMusic.stop();
		thirdMusic.stop();
		firstSampleMusic.stop();
		secondSampleMusic.stop();
		thirdSampleMusic.stop();
		
		if(index == 0) {
			firstMusic.start();
		}
		else if(index == 1) {
			secondMusic.start();
		}
		else {
			thirdMusic.start();
		}
		
	}
	
}