package com.larrykim.jpastudy;

import com.larrykim.jpastudy.domain.Member;
import com.larrykim.jpastudy.domain.Order;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class JpastudyApplication {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudyunit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            //멤버 5명과 각 멤버당 5개 주문 생성. 따라서, 총 15개 주문.
            System.out.println("트랜잭션 시작");
            generateMemberAndOrder(em);

            em.clear();

            //1
            testWithoutJpql(em);

            //2 JPQL일 경우
    //            testWithJpql(em);

            //3 지연로딩 일때 JPQL
    //            testWithJpqlOnLazyLoading(em);

            //4 페치 조인 사용
    //            testFetchJoin(em);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void generateMemberAndOrder(EntityManager em){

        int num=1;

        System.out.println(" 엔티티 생성 시작 ");
        // 멤버 5명과 각각 멤버가 주문 3개씩 갖게 만든다.
        for(long i=1; i<=5; i++){
            Member member = new Member( "멤버"+i);
            em.persist(member);

            for(int j=1; j<=3; j++){
                Order order = new Order("주문"+num);
                order.setMember(member);
                em.persist(order);
                num++;
            }
        }

    }

    public static void testWithoutJpql(EntityManager em){
        System.out.println("1>>>>>>>>>>>>>>>>>>>>>>>>");
        Member member = em.find(Member.class,1L);
        System.out.println("멤버1="+member.toString());
    }

    public static void testWithJpql(EntityManager em){
        // 처음에는 JPQL 대로 SQL이 나가지만
        // 이후에 주문 컬렉션이 즉시로딩인걸 알고 N+1 쿼리 실행 - 멤버수만큼 5개 더 나간다.
        System.out.println("2>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Member> members =
                em.createQuery("select m from Member m", Member.class)
                        .getResultList();
    }

    public static void testWithJpqlOnLazyLoading(EntityManager em){
        System.out.println("3>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Member> members =
                em.createQuery("select m from Member m", Member.class)
                        .getResultList();

//        Member firstMember = members.get(0);
//        System.out.println( "주문수량 = "+firstMember.getOrders().size()); //지연로딩 초기화

        //만약 아래 처럼 모든 멤버의 주문들을 가져올 경우... 쿼리가... 5개...
        System.out.println("4>>>>>>>>>>>>>>>>>>>>>>>>");
        for (Member member : members) {
            System.out.println("주문수량="+member.getOrders().size());
        }

        // 즉시로딩 - 조회시점 N+1 문제
        // 지연로딩 - 엔티티 사용시점 N+1 문제
    }

    public static void testFetchJoin(EntityManager em){
        System.out.println("5>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Member> members =
                em.createQuery("select distinct m from Member m JOIN FETCH m.orders", Member.class)
                        .getResultList();

        System.out.println("6>>>>>>>>>>>>>>>>>>>>>>>>");
        for (Member member : members) {
            System.out.println("주문수량=" + member.getOrders().size());
        }

        // 만약 outer join이 하고 싶을때는?? 엔티티그래프
    }

}
