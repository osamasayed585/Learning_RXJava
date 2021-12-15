package com.android.rxvisions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.edit_text);

//   تنقسم الـ RXJava الي 3 مفاهيم رئيسيه
//  الاول هو Observable
//   التاني هو الـ Operators
//   التالت هو Observers

/*
//                                                               Observable Types
//    Maybe :- ده بيبعت معلومة واحده ومهتم ب انها تكون وصلت او لا
//    Single :- معلومه واحدة ومهتم ب انها وصلت ولا لا وايه هو نوعها
//    Completable :- كذا معلومة ومش مهتم اي معلومه فيهم هو بس مهتم انها كملت ولا فيه مشكلة
//    Observable :- كذا معلومة ولـ Observer لسه بيقرأ الـ Observable  يهتم ان كملت ولا فيه مشكله واللي بعده
//    Flowable :- كذا معلومة ولـ Observer لسه بيقرأ الـ Observable  يهتم ان كملت ولا فيه مشكله واللي بعده ولو ملعومه وقعت اعمل ايه
//    Cold Observable :-  ده بشتغل لما يكون فيه Subscribe وكل واحد بيشتغل ف النص يرجهع يعيد له من الاول وموضح بالمثال
//    Hot Observable ->  موضح بالمثال
//       1- Connectable Observable
//       2- Behavior subject
//       3- Replay subject
//       4- Async subject


         // Cold Observable

        Observable<Long> cold = Observable.intervalRange(0,5,0,1,TimeUnit.SECONDS);

        cold.subscribe(i-> Log.d(TAG, "onCreate: Student 1 ="+i));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cold.subscribe(i-> Log.d(TAG, "onCreate: Student 2 ="+i));

*/
/*
        // Hot Observable -> convert from cold observable to hot observable

         ConnectableObservable<Long> hot = ConnectableObservable.intervalRange(0,5,0,1, TimeUnit.SECONDS).publish();
         hot.connect();
         hot.subscribe(i-> Log.d(TAG, "onCreate: student 1 = " + i) );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hot.subscribe(i-> Log.d(TAG, "onCreate: student 2 = " + i) );

 */
/*
        // Hot Observable -> PublishSubject

        PublishSubject<String> subject = PublishSubject.create();

        subject.subscribe(i-> Log.d(TAG, "onCreate: First Student = " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");

        subject.subscribe(i-> Log.d(TAG, "onCreate: Second Student = " + i));

        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("G");
        sleep(1000);
*/
/*        // Hot Observable -> Behavior subject

        BehaviorSubject<String> subject = BehaviorSubject.create();

        subject.subscribe(i-> Log.d(TAG, "onCreate: First Student = " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");

        subject.subscribe(i-> Log.d(TAG, "onCreate: Second Student = " + i));

        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("G");
        sleep(1000);
*/
/*
        // Hot Observable -> Replay subject

        ReplaySubject<String> subject = ReplaySubject.create();

        subject.subscribe(i-> Log.d(TAG, "onCreate: First Student = " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");

        subject.subscribe(i-> Log.d(TAG, "onCreate: Second Student = " + i));

        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("G");
        sleep(1000);
*/
/*
        // Hot Observable -> Async subject

        AsyncSubject<String> subject = AsyncSubject.create();

        subject.subscribe(i-> Log.d(TAG, "onCreate: First Student = " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");

        subject.subscribe(i-> Log.d(TAG, "onCreate: Second Student = " + i));

        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("RXJava with nerds");
        sleep(1000);
        subject.onComplete();
*/


        //                                      operators
/*
        // .Create (new ObservableOnSubscribe<Object> )

        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i =0; i<5; i++){
                    emitter.onNext("RXJava");
                }
                emitter.onComplete();
            }
        });
*/
        // .just (0,1,2,3,4)
/*
        Observable observable = Observable.just(0,1,2,3,4);
*/
/*
        // From Array
        Integer[] list = new Integer[5];
        list[0] = 0;
        list[1] = 1;
        list[2] = 2;
        list[3] = 3;
        list[4] = 4;

        Observable observable = Observable.fromArray(list);
*/

/*
        //   .range(start, count)
        Observable observable = Observable.range(0,4);

*/
/*


        Observable observable = Observable.timer(1, TimeUnit.SECONDS)
                .repeat(3);
*/
        // سوال.؟ لو حصل ايرور هل هيدخل فانكشن الكومبليت ولا لا
/*
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i=0; i<5; i++){
                    if (i == 3){
                        emitter.onNext(3/0);
                    }else {
                        emitter.onNext(i);
                    }
                }
            }
        });
*/
/*
//                                        Observer  ده اول نوع من انواع الــ
        Observer observer = new Observer() {
            @Override // اول م الـ "Observer" ده يدخل جوا ال "Observable" هيعمل ايه
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @Override // كل بيانات بتطلع جديد هعمل فيها ايه وبتيجي مع الـ O
            public void onNext(@NonNull Object o) {
                Log.d(TAG, "onNext: = "+ o);
                sleep(1000);

            }

            @Override // لما يحصل خطأ هعمل ايه والايرور جاي مع e
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: = "+ e.getMessage());
            }

            @Override // لما الحاجة دي تخلص انا هعمل فيها ايه
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        observable.subscribe(observer);
*/
 /*
       Observable.just(1, 2, 3, 4, 5)
                .subscribeOn(Schedulers.computation())
                .doOnNext(c -> Log.d(TAG, "UpStream: " + c + " Current Thread = " + Thread.currentThread().getName())) //  كل ما الـ Observable  يطلع حاجة اعمل ايه .. او ده الـ Upstream
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(o-> Log.d(TAG, "DownStream: " + o + " Current Thread = " + Thread.currentThread().getName()));
*/

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.length() != 0)
                            emitter.onNext(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        })
                .doOnNext(U -> Log.d(TAG, "os UpStream: :" + U))

//                .map(new Function<Object, Object>() { // لو عايز تعمل اي عملية ع الداتا قبل الـ observer  يبقي بأستخدام الماب
//                    @Override
//                    public Object apply(Object o) throws Throwable {
//                        return Integer.parseInt(o.toString()) * 2;
//                    }
//                })

//                .debounce(2,TimeUnit.SECONDS) // لو عايز تعطل ارسال الداتا الي الـ  observer مده معينه
//                .distinctUntilChanged() //  لو الداتا بعد م اتغيرت رجعت زي م هيه يبقي مافيش داعي اننا نعمل ارسال للـ observer

//                .filter(o -> !o.toString().equals("Osama")) // لو عايز تفلتر الداتا اللي رايحه لل Observer  و تحجب حاجة معينه

                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Throwable {
                        return sendData(o.toString());
                    }
                })
                .subscribe(D -> {
                    Log.d(TAG, "os DownStream: " + D);
//                    sendData(D.toString());
                });

    }


    public Observable sendData(String data) {
        Observable observable = Observable.just("Calling Api 1 to send " + data);
        observable.subscribe(o -> Log.d(TAG, "os sendData: " + o));
        return observable;
    }
}