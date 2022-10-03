package com.example.kotlin_study_002

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// 같은 이름의 함수 식별

interface Interface1 {
    fun funcA()
}

interface Interface2 {
    fun funcA()
}

abstract class Parent1 {
    abstract fun funcA()
}

class Child1 : Interface1, Interface2, Parent1() {
    override fun funcA() {
        Log.i("hsson", "Child1.funcA")
    }
}

interface Interface3 {
    fun funcB() {
        Log.i("hsson", "Interface3.funcB")
    }
}

abstract class Parent2 {
    abstract fun funcB()
}

class Child2 : Interface3, Parent2() {
    override fun funcB() { // 추상 함수 재정의
        Log.i("hsson", "Child2.funB")
    }

    fun funcC() {
        super.funcB() // Interface3.funcB 호출
        Log.i("hsson", "Child2.funcC")
    }
}

interface Interface4 {
    fun funcA() {
        Log.i("hsson", "Interface4.funcA")
    }
}

interface Interface5 {
    fun funcA() {
        Log.i("hsson", "Interface5.funcA")
    }
}

class Child3 : Interface4, Interface5 {
    override fun funcA() {
        super<Interface4>.funcA()
        super<Interface5>.funcA() // super 를 통해 인터페이스 사용 가능
        Log.i("hsson", "Child3.funcA")
    }

    fun funcB() { // 일반 함수도 가능
        super<Interface4>.funcA()
        super<Interface5>.funcA()
        Log.i("hsson", "Child3.funcB")
    }

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 같은 이름의 함수가 인터페이스, 추상클래스에 구현된 경우 (추상 함수로 구현)
        // 단 해당 함수에 구현부가 없는 경우
        // 구현부가 없기에 구분 필요 없음. 구분 없이 사용.
        val child1 = Child1()
        child1.funcA() // result : Child1.funcA

        // 같은 이름의 함수가 인터페이스에 구현, 추상 함수로 구현된 경우
        // 단 인터페이스에는 구현부가 있는 경우
        // overriding 으로 추상함수 재구현
        // super 로 인터페이스 호출
        val child2 = Child2()
        child2.funcB() // result : Child2.funB
        child2.funcC() // result : Interface3.funcB\nChild2.funcC

        // 인터페이스에 같은 이름의 함수가 여러개 있는 경우
        val child3 = Child3()
        child3.funcA() // result : Interface4.funcA\nInterface5.funcA\nChild3.funcA
        child3.funcB() // result : Interface4.funcA\nInterface5.funcA\nChild3.funcB
    }
}