package com.example.airconmoa.ui.join_company.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.airconmoa.databinding.DialogLocationRecycleBinding
import com.example.airconmoa.databinding.FragmentCompanyJoinWhereBinding
import com.example.airconmoa.databinding.ItemRvLocationBinding
import com.example.airconmoa.ui.join_company.Adapter.DialogLocationRecyclerAdapter

class LocationDoRecycleDialog(context: Context,dlgbinding:FragmentCompanyJoinWhereBinding,locationDo:String):Dialog(context) {

    private lateinit var binding: DialogLocationRecycleBinding

    val Do = locationDo

    private lateinit var savelocation : List<String>


    private var count = 0
    private var size = 0
    private var span = 0

    val obj = object : DialogLocationRecyclerAdapter.OnClickInterface {
        override fun onClick(view: View, position: Int) {
            Log.d("Tester", "onClick: ${locationlist[position]}")
            dlgbinding.txtDo.text = locationlist[position]
            dismiss()
        }

        override fun onClick(view: ItemRvLocationBinding, position: Int) {

        }

    }
    val obj2 = object : DialogLocationRecyclerAdapter.OnLongClickInterface {
        override fun onLongClick(view: View, position: Int) {

        }

        override fun onLongClick(view: ItemRvLocationBinding, position: Int) {

        }

    }
    val itemClickInterface: DialogLocationRecyclerAdapter.OnClickInterface = obj
    val itemLongClickInterface: DialogLocationRecyclerAdapter.OnLongClickInterface = obj2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogLocationRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)



        for(i:Int in 0..locationlist.size){
            if(Do == locationlist[i]){

                val listadapter = DialogLocationRecyclerAdapter(
                    context,
                    location[i], itemClickInterface, itemLongClickInterface

                )
                binding.locationRecyclerView.adapter = listadapter
                savelocation = location[i]
                break
            }
        }



        var listManager = GridLayoutManager(context, 4)

        binding.locationRecyclerView.apply {
            layoutManager = listManager
        }

        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setGravity(Gravity.BOTTOM)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)


    }
    val locationlist : List<String> = listOf(
        "서울", "세종", "강원", "인천",
        "경기", "충북", "충남", "경북",
        "대전", "대구", "전북", "경남",
        "울산", "광주", "부산", "전남", "제주"
    )
    val Seoul:List<String> = listOf(
        "관악구", "강남구", "강동구", "강북구",
        "강서구", "광진구", "구로구", "금천구",
        "노원구", "도봉구", "동대문구", "동작구",
        "마포구", "서대문구", "서초구", "성동구",
        "성북구", "송파구", "양천구", "용산구",
        "영등포구", "은평구", "종로구", "중구", "중량구"
    )
    val SeJong : List<String> = listOf(
        "가람동", "고운동", "금남면", "나성동",
        "다정동", "대평동", "도담동", "반곡동",
        "보람동", "부강면", "새롬동", "소담동",
        "소정면", "아름동", "어진동", "연기면",
        "연동면", "연서면", "장군면", "전동면",
        "전의면", "존촌동", "조치원읍", "한솔동",
        "집현동", "해밀동"
    )

    val GangWon : List<String> = listOf(
        "강릉시", "고성군", "동해시", "삼척시",
        "속초시", "양구군", "양양군", "영월군",
        "원주시", "인제군", "정성군", "철원군",
        "춘천시", "태백시", "평창군", "홍천군",
        "화천", "횡성군"
    )

    val InCheon: List<String> = listOf(
        "강화군", "계양군", "미추홀구", "남동구",
        "동구", "부평구", "서구", "연수구",
        "옹진군", "중구"
    )
    val GyeongGi : List<String> = listOf(
        "가평군", "고양시 덕양구", "구리시", "고양시 일산동구",
        "고양시 일산서구", "과천시", "광명시", "광주시",
        "군포시", "김포시", "남양주시", "부천시",
        "시흥시", "동두천시", "성남시 분당구", "여주시",
        "성남시 수정구", "성남시 중원구", "수원시 영통구", "수원시 장안구",
        "수원시 팔달구", "안산시 상록구", "안성시", "연천군", "안양시 동안구",
        "오산시", "양평군", "안양시 만안구", "양주시",
        "의왕시", "용인시 기흥구", "평택시", "포천시",
        "용인시 수지구", "용인시 처인구", "의정부시", "이천시",
        "파주시", "하남시", "화성시"
    )
    val ChungBuk : List<String> = listOf(
        "괴산군", "단양군", "보은구", "영동군",
        "옥천군", "음성군", "제천시", "증평군",
        "진천군", "청주시 상당구", "청주시 서원구", "청주시 청원구",
        "청주시 흥덕구", "충주시"
    )

    val ChungNam : List<String> = listOf(
        "계룡시", "공산시", "금산군", "논산시",
        "당진시", "보령시", "부여시", "부여군",
        "서산시", "서천군", "아산시", "예산군",
        "천안시 동남구", "천안시 서북구", "청양군",
        "태안군", "홍성군"
    )

    val GyeongBuk : List<String> = listOf(
        "경산시", "경주시", "고령군", "구미시",
        "김천시", "문경시", "봉화군", "상주시",
        "성주군", "안동시", "영덕군", "영양군",
        "영주시", "영천시", "예천군", "울릉군",
        "을지군", "의성군", "청도군", "청송군",
        "칠곡군", "포항시 남구", "포항시 북구"
    )

    val DaeJeon : List<String> = listOf(
        "대덕구", "동구", "서구", "유성구",
        "중구"
    )

    val DaeGu : List<String> = listOf(
        "군위군", "남구", "달서구", "달성군",
        "동구", "북구", "서구", "수성구",
        "중구"
    )

    val JeonBuk : List<String> = listOf(
        "고창군", "군산시", "김제시", "남원시",
        "무주군", "부안군", "순창군", "완주군",
        "익산시", "임실군", "장수군", "전주시 덕진구",
        "전주시 완산구", "정읍시", "진안군"
    )

    val GyeongNam : List<String> = listOf(
        "거제시", "거창군", "고성군", "김해시",
        "남해군", "밀양시", "사천시", "산청군",
        "양산시", "의령군", "진주시", "창녕군",
        "창원시 마산합포구", "창원시 마산회원구", "창원시 성산구", "창원시 의창구",
        "창원시 진해구", "통영시", "하동군", "함안군",
        "함양군", "합천군"
    )

    val UlSan : List<String> = listOf(
        "남구", "동구", "북구", "울주군",
        "중구"
    )

    val GwangJu : List<String> = listOf(
        "광산구", "남구", "동구", "북구",
        "서구"
    )

    val BuSan : List<String> = listOf(
        "강서구", "금정구", "기장군", "남구",
        "동구", "동래구", "부산진구", "북구",
        "사상구", "사하구", "서구", "수영구",
        "연제구", "영도구", "중구", "해운대구"
    )

    val JeonNam : List<String> = listOf(
        "강진구", "고흥구", "곡성군", "광양시",
        "구례군", "나주시", "담양군", "목포시",
        "무안군", "보령군", "순천시", "신안군",
        "여수시", "영광군", "영암군", "완도군",
        "장성군", "장흥군", "진도군", "함평군",
        "해남군", "화순군"
    )

    val JeJu : List<String> = listOf(
        "서구포시", "제주시"
    )
    val location : MutableList<List<String>> = mutableListOf(
        Seoul, SeJong, GangWon, InCheon,
        GyeongGi, ChungBuk, ChungNam, GyeongBuk,
        DaeJeon, DaeGu, JeonBuk, GyeongNam,
        UlSan, GwangJu, BuSan, JeonNam, JeJu


    )

}