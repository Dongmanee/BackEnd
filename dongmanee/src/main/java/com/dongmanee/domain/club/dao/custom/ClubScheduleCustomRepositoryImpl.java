package com.dongmanee.domain.club.dao.custom;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.dongmanee.domain.club.domain.ClubSchedule;
import com.dongmanee.domain.club.domain.QClubSchedule;
import com.dongmanee.domain.club.dto.request.RequestClubScheduleSearchCriteria;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClubScheduleCustomRepositoryImpl implements ClubScheduleCustomRepository {
	private final JPAQueryFactory query;

	@Override
	public Slice<ClubSchedule> findAllBySearchCriteriaBeforeCursor(long clubId, LocalDateTime cursor,
		RequestClubScheduleSearchCriteria searchCriteria, Pageable pageable) {
		QClubSchedule clubSchedule = QClubSchedule.clubSchedule;

		BooleanBuilder builder = new BooleanBuilder();

		if (cursor != null) {
			builder.and(clubSchedule.startTime.lt(cursor));
		}

		if (searchCriteria.getStartDate() != null) {
			builder.and(clubSchedule.startTime.goe(searchCriteria.getStartDate().atStartOfDay()));
		}

		if (searchCriteria.getEndDate() != null) {
			builder.and(clubSchedule.endTime.loe(searchCriteria.getEndDate().atTime(LocalTime.MAX)));
		}

		List<ClubSchedule> clubScheduleList = query.select(clubSchedule)
			.from(clubSchedule)
			.where(clubSchedule.club.id.eq(clubId))
			.where(builder)
			.orderBy(clubSchedule.startTime.desc())
			.orderBy(clubSchedule.id.desc())
			.limit(pageable.getPageSize())
			.fetch();
		return new SliceImpl<>(clubScheduleList, pageable, hasNextPage(clubScheduleList, pageable.getPageSize()));
	}

	private <T> boolean hasNextPage(List<T> contents, int pageSize) {
		if (contents.size() > pageSize) {
			contents.remove(pageSize);
			return true;
		}
		return false;
	}
}
