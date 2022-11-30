package tw.thirdteam.model;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class MemberDAO {

	@Autowired
	private SessionFactory factory;

	public Member selectById(int memberid) {
		Session session = factory.openSession();
		return session.get(Member.class, memberid);
	}

	public Member login(String memberemail, String memberpassword) {
		Session session = factory.openSession();
		Query<Member> query = session.createQuery("from Member where memberemail=?0 and memberpassword=?1",
				Member.class);
		query.setParameter(0, memberemail);
		query.setParameter(1, memberpassword);
		Member member = query.uniqueResult();
		session.close();
		return member;
	}

	public List<Member> selectAll() {
		Session session = factory.openSession();
		Query<Member> query = session.createQuery("from Member", Member.class);
		session.close();
		return query.list();
	}

	public Member insert(Member member) {
		Session session = factory.openSession();
		Member rs = session.get(Member.class, member.getMemberid());
		session.close();
		if (rs == null) {
			session.save(member);
			return member;
		}
		return null;
	}

	public Member update(Member member) {
		Session session = factory.openSession();
		Member rs = session.get(Member.class, member.getMemberid());
		session.close();
		if (rs != null) {
			rs.setMembername(member.getMembername());
			rs.setMemberemail(member.getMemberemail());
			rs.setMemberpassword(member.getMemberpassword());
			rs.setMembermobile(member.getMembermobile());
			rs.setMemberlevel(member.getMemberlevel());
		}
		return rs;
	}

	public Member changeLevelToStop(int memberid) {
		Session session = factory.openSession();
		Member rs = session.get(Member.class, memberid);
		session.close();
		if (rs != null) {
			rs.setMemberlevel("停用");
		}
		return rs;
	}

	public Member changeLevelToUser(int memberid) {
		Session session = factory.openSession();
		Member rs = session.get(Member.class, memberid);
		session.close();
		if (rs != null) {
			rs.setMemberlevel("一般會員");
		}
		return rs;
	}

	public boolean deleteById(int memberid) {
		Session session = factory.openSession();
		Member rs = session.get(Member.class, memberid);
		session.close();
		if (rs != null) {
			session.delete(rs);
			return true;
		}
		return false;
	}

}
