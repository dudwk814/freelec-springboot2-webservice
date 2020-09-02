package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
//해당 클래스를 상속하는 클래스의 필드가 컬럼으로 인식된다.
//(해당 클래스를 상속받으면 상속 받은 Entity클래스에 createdDate, modifiedDate필드가 컬럼으로 추가된다,)
@MappedSuperclass
//해당 클래스에 Auditing기능을 포함시킨다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // Entity가 생성되어 저장될 때 시간이 자동으로 저장된다.
    @CreatedDate
    private LocalDateTime createdDate;

    //조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
