package com.anomie.webservice.practice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QtestLombok is a Querydsl query type for testLombok
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QtestLombok extends EntityPathBase<testLombok> {

    private static final long serialVersionUID = -1111744922L;

    public static final QtestLombok testLombok = new QtestLombok("testLombok");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QtestLombok(String variable) {
        super(testLombok.class, forVariable(variable));
    }

    public QtestLombok(Path<? extends testLombok> path) {
        super(path.getType(), path.getMetadata());
    }

    public QtestLombok(PathMetadata metadata) {
        super(testLombok.class, metadata);
    }

}

