package com.example.happylife.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author by syt
 * on 2021/8/13
 * desc:栏目实体
 * tableName 表名称
 * Entity必须要要有一个主键，可以用@PrimaryKey注解标记主键，autoGenerate表示这个主键是否是自增长
 */
@Entity(tableName = "column_list_tags")
data class CarouselNews(
    @PrimaryKey(autoGenerate = false)
    val Id: Long,
    val Title: String,
    val ImgUrl: String,

    // 在数组中的序列
    @ColumnInfo(name = "tag_sequence")
    var sequence: Int
)
