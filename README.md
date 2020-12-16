
  # 미니메이플스토리
 1. JAVA언어를 공부하면서 평소 좋아하던 게임을 만들자는 생각에 메이플스토리를 모티브삼아 프로젝트를 제작하게 되었습니다. 제작 기간은 2주정도 걸렸고 총 3명이 합작하여 만들었습니다

 - Git-Hub주소 - https://github.com/KuTaeMo/JavaProject/tree/master


 - 만들고 싶었던 모티브

<img src="https://blog.kakaocdn.net/dn/nHNsc/btqCy4ZVqWE/5SY5SCDtkc7TNQnu7N2G30/img.png"  width="600" height="370">


 ## 실제 구현한 모습
 
 - 시작 화면
 <div>
	<img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_172.png?raw=true"  width="450" height="370"> <img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_173.png?raw=true"  width="450" height="370">	
</div>
<br>

- 로그인 화면
<div>
	<img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_174.png?raw=true"  width="450" height="370">
	<img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_175.png?raw=true"  width="450" height="370">
</div>
<br>

- 실행 화면
<div>
	<img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_176.png?raw=true"  width="600" height="370">
</div>
<br>

- 공격 및 스킬
<div>
	<img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_178.png?raw=true"  width="450" height="370"> <img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_177.png?raw=true"  width="450" height="370">
</div>
<br>

- 보스
<div>
	<img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_179.png?raw=true"  width="450" height="370"> <img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_180.png?raw=true"  width="450" height="370">
</div>
<br>

- 죽음
<div>
	<img src="https://github.com/KuTaeMo/JavaProject/blob/master/introImage/Screenshot_181.png?raw=true"  width="600" height="370">
</div>
<br>

## 설치 및 실행
1. 설치
 - SpringToolSuite4.exe툴을 사용하여 제작했으며, 위의 파일을 다운받아서 export하시면 되겠습니다.
 - 이클립스도 실행이 될 것 같지만 SpringToolSuite4를 사용하시는걸 권장합니다.
2. 실행
 - Loading클래스를 Run해서 실행 시키면 게임을 진행 할 수 있습니다.


## 중요클래스에 대한 설명

 - [Player](#player)
 - [MapleApp](#mapleapp)
 - [Loading](#loading)
 - [Enemy](#enemy)
 - [GamePoint](#gamepoint)
 - [Skill](#skill)
 - [Enemy클래스를 상속받는 몬스터클래스](#monster)

## Player
 - 사용자가 플레이하는 플레이어 JLabel클래스, 클래스 내부에는 플레이어가 움직일 수 있는 좌, 우, 점프 등의 메소드가 존재 스레드 사용이 많다보니 작은 렉이 발생
 아래와 같이 움직이는 소스코드가 주된 메소드이다. 맵에 맞췄기 때문에 점프할때의 위치 요소들이 크게 작용 (아래는 오른족으로 이동할때의 메소드)
``` JAVA
public void moveRight1() {
      if (isRight == false) {
         new Thread(new Runnable() {
            @Override
            public void run() {
               seewhere = true;
               isRight = true;
               while (isRight && hp > 0) {
                  moveRangeR();
                  // 오른쪽으로 보는중
                  setLocation(x, y); // 내부에 repaint() 존재
                  try {
                     Thread.sleep(3);
                     setIcon(icPlayerW);
                     Thread.sleep(3);
                     setIcon(icPlayerR);

                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               setIcon(icPlayerW);

            }
         }).start();
      }
   }
```

## MapleApp
 - 게임이 실행되는 틀 JFrame 여기에서 게임이 진행. 공격했을때의 충돌을 검사하는 메소드나, 다른 모든 객체를 생성, HP,MP검사등을 함. 마찬가지로 Thread사용이 많아 잦은 렉이 발생
 중요 코드로는 플레이어와 몬스터 간 거리를 계산하여 충돌을 검사하는 메소드가 존재
 
``` JAVA
	public boolean crash(int playerX, int playerY, int enemyX, int enemyY, int playerW, int playerH, int enemyW,
			int enemyH) {
		boolean check = false;
		if (Math.abs((playerX + (playerW / 2)) - (enemyX + enemyW / 2 + 20)) < (enemyW / 2 + playerW / 2 - 60)
				&& Math.abs((playerY + playerH / 2) - (enemyY + enemyH / 2)) < (enemyH / 2 + playerH / 2)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}
```
  
## Loading
 - 게임이 시작전 인트로 역할 (비밀번호 : maple)

## Enemy
 - 몬스터들이 상속해야하는 JLable 추상클래스 나머지 몬스터들은 이 클래스 형식에 맞춰서 제작
 상속 받는 행위, 상태
 
``` JAVA
 
	Enemy enemy = this;
	ImageIcon enemyMove;
	final static String TAG = "Enemy : ";
	//좌표값
	int x;
	int y;
	int speed;
	int moveState;
	int hp;
	int width;
	int height;
	String name;
	Random random = new Random();
	Timer timer = new Timer();
```
 
``` JAVA
 
	public Enemy() {
		
	}
	public Enemy(String string, int x, int y) {
		
	}
	public void moveChange(){
		
	}
	public void moveDirection() {
		
	}
```
 
## GamePoint
 - 게임이 시작하고 몬스터를 처치함에 따라 올라가는 포인트 점수, 일정 포인트 이상으로 오르면 보스룸 입장
 
## Skill
 - 스킬을 사용했을때 나오는 스킬 JLabel 스킬또한 하나의 객체로 인식하여 몬스터간 거리를 계산, 충돌하는 클래스
스킬 클래스의 메소드 일부 움직임은 플레이어의 이동과 비슷

``` JAVA
 public void Col(ArrayList<Enemy> enemy) {
      for (int i = 0; i < enemy.size(); i++) {
	 if (crash(this.x, this.y, enemy.get(i).x, enemy.get(i).y, this.width, this.height, enemy.get(i).width,
	       enemy.get(i).height)) {
	    System.out.println("스킬 적중!");
	    this.isSkill = false;
	    setIcon(null);
	    enemy.get(i).hp -= 20;
	    System.out.println(enemy.get(i).name + " hp :" + enemy.get(i).hp);
```
        
## Enemy클래스를 상속받는 몬스터클래스
  - 머쉬맘, 스톤골렘, 발록등의 몬스터가 존재하며 Enemy클래스를 상속받는 클래스
  랜덤하게 움직여야 하기 때문에 움직임을 랜덤하게 동작하는 행위가 존재
  - 현재 시간값을 사용하여 변칙적인 행동을 가능하게 함
  
``` JAVA
  
 	 public void moveChange() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					
					long n = System.currentTimeMillis()/1000;
					if(n % 5 == 0) {
						speed = 100;
						moveState = random.nextInt(3);
					}
					speed = 5;	
				}	
			}
		}).start();
	}
```

## 만들어본 후기
 - 무슨 게임을 할지 생각하다가 예전에 존재했던 주니어네이버의 메이플스토리 미니게임이 생각이 나서 만들어 보았다. 각 객체들을 따로따로 움직이게 해야하고 움직임에대한 모션, 충돌같은 검사 같은 요소들이 생각만큼 쉽지 않았다. 하지만 3명이서 각각 역할을 나누어 제작하다보니 혼자서 프로젝트를 진행 했을때 보다 안정적이고 빠르게 제작할 수 있었다. 자바 언어로 게임을 만든게 처음이라 많이 부족하지만 개발자로서의 큰 한걸음이 되었음을 느낀다.

## 부족한점
 - 플레이어가 움직일때의 모션이라던지, 맞았을 때의 이펙트, 스레드를 사용할때의 렉발생 등이 많이 부족했다. 



