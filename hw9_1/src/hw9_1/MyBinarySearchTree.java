package hw9_1;
import java.util.NoSuchElementException;

public class MyBinarySearchTree {
	// (1) 트리의 노드를 표현하는 private 클래스 Node - 필드(int형 key, leftChild, rightChild), 생성자(key값을 매개변수로 받아 초기화)
	class Node{
		int key;
		Node leftChild;
		Node rightChild;
		Node (int i) {
			this.key=i;
			this.leftChild=null;
			this.rightChild=null;
		}
	}
	// (2) private 인스턴스 변수 트리의 루트 노드를 가킬 변수(root)를 선언하고 null로 초기화
	Node root=null;
	// 트리에 키값 key를 삽입하는 메소드
	public void insert(int key) {
		root = insertKey(root, key);
	}
	// p를 루트로 하는 트리에 키값 key를 삽입하고, 삽입 후 루트를 리턴하는 메소드(재귀 알고리즘)
	private Node insertKey(Node p, int key) {
		if(p == null) {  
			Node newNode = new Node(key);
			return newNode; 
		}
		else if(key < p.key) {
			p.leftChild = insertKey(p.leftChild, key);
			return p;  // 루트 불변
		}
		else if(key > p.key) {
			p.rightChild = insertKey(p.rightChild, key);
			return p;  // 루트 불변       
		}
		else {  // key = p.key 인 경우 삽입 실패
			System.out.println("삽입 실패. 중복된 키값이 존재합니다: " + key);
			return p;   // 루트 불변
		}
	}  
	// 트리를 중위순회하며 출력하는 메소드
	public void printInorder() {
		inorder(root);
	}
	// (3) p를 루트로 하는 트리를 중위 순회하며 키값을 출력하는 메소드(재귀 알고리즘)
	private void inorder(Node p) {
		if(p!=null) {
			inorder(p.leftChild);
			System.out.println(p.key);
			inorder(p.rightChild);
		}
	}
	// (4) 트리의 최대 키값을 리턴하는 메소드(반복 알고리즘) - 공백 트리인 경우 NoSuchElementException 예외 발생
	public int max() {
		if(root == null) {
			throw new NoSuchElementException();
		}
		Node r=root;
		while(r.rightChild!=null) r=r.rightChild;
		return r.key;  // 임시로 추가한 문장임
	}
	// 트리가 키값 key를 포함하는지 여부를 리턴하는 메소드
	public boolean contains(int key) {
		return search(root, key);
	}
	// (5) p를 루트로 하는 트리에 키값 key가 존재하는지 여부를 리턴하는 메소드(재귀 알고리즘)
	private boolean search(Node p, int key) {
		if(p==null) return false;
		if(p.key==key) return true;
		if(p.key>key) return search(p.leftChild,key);
		if(p.key<key) return search(p.rightChild,key);
		return false;  // 임시로 추가한 문장임
	}
	// (6) 트리에 키값 key를 삽입하는 메소드(반복 알고리즘) - 삽입 성공여부(true/false)를 리턴
	public boolean add(int key) {
		/* 실패
		Node r=root;
		if(root==null) {
			root=new Node(key);
			return true;
		}
		while(!(r.leftChild==null || r.rightChild==null)) {
			if(r.key==key) return false;
			if (r.key>key) {
				r=r.leftChild;
			}
			if(r.key<key) {
				r=r.rightChild;
			}
		}
		if(r.rightChild!=null) r=r.rightChild;
		if(r.leftChild!=null) r=r.leftChild;
		if((r.leftChild==null&&r.rightChild==null)) {
			if(r.key==key) return false;
			if (r.key>key) r.leftChild=new Node(key);
			if (r.key<key) r.rightChild=new Node(key);
		} */
		Node n = new Node(key);
		if(root==null){
			root = n;
			return true;
		}
		Node s = root;
		Node p = null;
		while(true){
			p = s;
			if(key==s.key) return false;
			if(key < s.key){                
				s = s.leftChild;
				if(s==null){
					p.leftChild=n;
					return true;
				}
			}else{
				s = s.rightChild;
				if(s==null){
					p.rightChild=(n);
					return true;
				}
			}
		}
	}
	public boolean remove(int key) {
		if(root==null) return false;
		Node s=root;
		Node p=root;
		boolean LR=false;
		while(key!=s.key) {
			p=s;
			if(s.key>key) {
				s=s.leftChild;
				LR=true;
			}
			else {
				s=s.rightChild;
				LR=false;
			}
			if (s==null) return false;
		}
		if(s.leftChild==null&&s.rightChild==null) {
			// System.out.println("case 1"); 삭제 작동 확인 출력문
			if(s==root) root=null;
			if(LR) p.leftChild=null;
			else p.rightChild=null;
			return true;
		}
		else if (s.rightChild==null) {
			// System.out.println("case 2"); 삭제 작동 확인 출력문
			if(s==root) root=s.leftChild;
			else if(LR) p.leftChild=s.leftChild;
			else p.rightChild=s.leftChild;
		}
		else if (s.leftChild==null){
			// System.out.println("case 2"); 삭제 작동 확인 출력문
			if(s==root) root=s.rightChild;
			else if(LR) p.leftChild=s.rightChild;
			else p.rightChild=s.rightChild;
		}
		else if (!(s.leftChild==null&&s.rightChild==null)) {
			// System.out.println("case 3"); 삭제 작동 확인 출력문
			Node sc=null;
			Node sp=null;
			Node n=s.leftChild;
			while(n!=null) {
				sp=sc;
				sc=n;
				n=n.rightChild;
			}
			if(sc!=s.leftChild) {
				sp.rightChild=sc.leftChild;
				sc.leftChild=s.leftChild;
			}
			if(s==root) root=sc;
			else if (LR) p.leftChild=sc;
			else p.rightChild=sc;
			sc.rightChild=s.rightChild;
		}
		return true;
	}
}