package tw.thirdteam.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="memberService")
@Transactional
public class MemberService implements IMemberService {

	@Autowired
	private MemberDAO mDao;

	public Member selectById(int memberid) {
		return mDao.selectById(memberid);
	}

	public Member login(String memberemail, String memberpassword) {
		return mDao.login(memberemail, memberpassword);
	}

	public List<Member> selectAll() {
		return mDao.selectAll();
	}

	public Member insert(Member member) {
		return mDao.insert(member);
	}

	public Member update(Member member) {
		return mDao.update(member);
	}

	public Member changeLevelToStop(int memberid) {
		return mDao.changeLevelToStop(memberid);
	}

	public Member changeLevelToUser(int memberid) {
		return mDao.changeLevelToUser(memberid);
	}

	public boolean deleteById(int memberid) {
		return mDao.deleteById(memberid);
	}

}
