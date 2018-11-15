package hw9_1;
import java.util.NoSuchElementException;

public class MyBinarySearchTree {
	// (1) Ʈ���� ��带 ǥ���ϴ� private Ŭ���� Node - �ʵ�(int�� key, leftChild, rightChild), ������(key���� �Ű������� �޾� �ʱ�ȭ)
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
	// (2) private �ν��Ͻ� ���� Ʈ���� ��Ʈ ��带 ��ų ����(root)�� �����ϰ� null�� �ʱ�ȭ
	Node root=null;
	// Ʈ���� Ű�� key�� �����ϴ� �޼ҵ�
	public void insert(int key) {
		root = insertKey(root, key);
	}
	// p�� ��Ʈ�� �ϴ� Ʈ���� Ű�� key�� �����ϰ�, ���� �� ��Ʈ�� �����ϴ� �޼ҵ�(��� �˰���)
	private Node insertKey(Node p, int key) {
		if(p == null) {  
			Node newNode = new Node(key);
			return newNode; 
		}
		else if(key < p.key) {
			p.leftChild = insertKey(p.leftChild, key);
			return p;  // ��Ʈ �Һ�
		}
		else if(key > p.key) {
			p.rightChild = insertKey(p.rightChild, key);
			return p;  // ��Ʈ �Һ�       
		}
		else {  // key = p.key �� ��� ���� ����
			System.out.println("���� ����. �ߺ��� Ű���� �����մϴ�: " + key);
			return p;   // ��Ʈ �Һ�
		}
	}  
	// Ʈ���� ������ȸ�ϸ� ����ϴ� �޼ҵ�
	public void printInorder() {
		inorder(root);
	}
	// (3) p�� ��Ʈ�� �ϴ� Ʈ���� ���� ��ȸ�ϸ� Ű���� ����ϴ� �޼ҵ�(��� �˰���)
	private void inorder(Node p) {
		if(p!=null) {
			inorder(p.leftChild);
			System.out.println(p.key);
			inorder(p.rightChild);
		}
	}
	// (4) Ʈ���� �ִ� Ű���� �����ϴ� �޼ҵ�(�ݺ� �˰���) - ���� Ʈ���� ��� NoSuchElementException ���� �߻�
	public int max() {
		if(root == null) {
			throw new NoSuchElementException();
		}
		Node r=root;
		while(r.rightChild!=null) r=r.rightChild;
		return r.key;  // �ӽ÷� �߰��� ������
	}
	// Ʈ���� Ű�� key�� �����ϴ��� ���θ� �����ϴ� �޼ҵ�
	public boolean contains(int key) {
		return search(root, key);
	}
	// (5) p�� ��Ʈ�� �ϴ� Ʈ���� Ű�� key�� �����ϴ��� ���θ� �����ϴ� �޼ҵ�(��� �˰���)
	private boolean search(Node p, int key) {
		if(p==null) return false;
		if(p.key==key) return true;
		if(p.key>key) return search(p.leftChild,key);
		if(p.key<key) return search(p.rightChild,key);
		return false;  // �ӽ÷� �߰��� ������
	}
	// (6) Ʈ���� Ű�� key�� �����ϴ� �޼ҵ�(�ݺ� �˰���) - ���� ��������(true/false)�� ����
	public boolean add(int key) {
		/* ����
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
			// System.out.println("case 1"); ���� �۵� Ȯ�� ��¹�
			if(s==root) root=null;
			if(LR) p.leftChild=null;
			else p.rightChild=null;
			return true;
		}
		else if (s.rightChild==null) {
			// System.out.println("case 2"); ���� �۵� Ȯ�� ��¹�
			if(s==root) root=s.leftChild;
			else if(LR) p.leftChild=s.leftChild;
			else p.rightChild=s.leftChild;
		}
		else if (s.leftChild==null){
			// System.out.println("case 2"); ���� �۵� Ȯ�� ��¹�
			if(s==root) root=s.rightChild;
			else if(LR) p.leftChild=s.rightChild;
			else p.rightChild=s.rightChild;
		}
		else if (!(s.leftChild==null&&s.rightChild==null)) {
			// System.out.println("case 3"); ���� �۵� Ȯ�� ��¹�
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