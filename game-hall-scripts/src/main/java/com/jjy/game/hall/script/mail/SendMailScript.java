package com.jjy.game.hall.script.mail;

import java.util.Date;
import java.util.function.Consumer;

import com.jjy.game.hall.script.IMailScript;
import com.jjy.game.model.mongo.hall.dao.MailDao;
import com.jjy.game.model.struct.Mail;
import com.jjy.game.model.struct.Mail.MailState;
import com.jjy.game.model.struct.Mail.MailType;

/**
 * 发送邮件
 * 
 * @author JiangZhiYong
 * @QQ 359135103 2017年9月21日 下午4:52:57
 */
public class SendMailScript implements IMailScript {

	@Override
	public void sendMail(long senderId, long receiverId, String title, String content, MailType type,
			Consumer<Mail> mailConsumer) {
		Mail mail = new Mail();
		mail.setTitle(title);
		mail.setContent(content);
		mail.setType(type.ordinal());
		mail.setCreateTime(new Date());
		mail.setSenderId(senderId);
		mail.setReceiverId(receiverId);
		mail.setState(MailState.NEW.ordinal());
		if (mailConsumer != null) {
			mailConsumer.accept(mail);
		}
		MailDao.saveMail(mail);

	}

}
