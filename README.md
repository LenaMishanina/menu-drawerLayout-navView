<h2>Урок 30</h2>

DrawerLayout - контейнер,в который можно добавить элементы, которые будут выдвигаться слева направо или справа налево
часто используется с NavigationView (см урок 31)

1. Создаем DrawerLayout в xml
2. Создаем основной контейнер в DrawerLayout (который будет отображать основной контент на экране)
3. Создаем элемент, который будет выдвигаться, в DrawerLayout (Этому элементу нужно указать аттрибут layout_gravity)
  
  start - слева направо; end - справа налево
```
<DrawerLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        android:id="@+id/content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"/>
    <TextView
        android:id="@+id/textView"
        android:layout_width="200dp"
        android:layout_height="match_parent"
        android:text="TextView"
        android:layout_gravity="start"/>
</androidx.drawerlayout.widget.DrawerLayout>
```
***Обычно вместо TextView ставят, например, NavigationView, чтобы сделать меню (menu)

4. Задвинуть или выдвинуть DrawerLayout в коде:
   ```
        binding.apply {
            btnOpen.setOnClickListener {
                main.openDrawer(GravityCompat.START)//выдвинуть слева направо
                //main.closeDrawer(GravityCompat.START)//задвинуть слева направо
            }
        }
   ```
btnOpen - кнопка в основном контенте (который не выдвигается)


<h2>Урок 31</h2>
1. В DrawerLayout поставим NavigationView (xml)

```
<DrawerLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:openDrawer="start"> 		//!!!чтобы на дизайне Drawer был открыт (на само приложение не повлияет, тк tools)

    <LinearLayout
        android:id="@+id/content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:gravity="center">

        <TextView
            android:id="@+id/textView2"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Main content" />

        <Button
            android:id="@+id/btnOpen"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Open Menu" />

    </LinearLayout>

    <NavigationView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"/>

</androidx.drawerlayout.widget.DrawerLayout>
```
2. Добавим в NavigationView категории
   
	2.1. res-> menu -> nav_menu.xml   
```
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"	2.2 Подключаем библиотеку для работы с navigation
    tools:showIn="navigation_view">	2.3 разметку меню делаем открытой (то жк самое tools:openDrawer="start")
    <item android:title="category 1">	2.4 создаем категории и items внутри
        <menu>
            <item
                android:id="@+id/item1"
                android:icon="@drawable/ic_android_black_24dp"
                android:title="item 1"/>
            <item
                android:id="@+id/item2"
                android:icon="@drawable/ic_android_black_24dp"
                android:title="item 2"/>
            <item
                android:id="@+id/item3"
                android:icon="@drawable/ic_android_black_24dp"
                android:title="item 3"/>
            <item
                android:id="@+id/item4"
                android:icon="@drawable/ic_android_black_24dp"
                android:title="item 4"/>

        </menu>
    </item>
</menu>
```
3. в NavigationView установить созданное menu
```
<NavigationView
        android:id="@+id/navView"
        app:menu="@menu/nav_menu"/>
```
4. Обработка нажатия элементов в коде
```
    private fun initNavView() = with(binding) {
        navView.setNavigationItemSelectedListener {
            //it - item на который нажали
            when (it.itemId) {
                R.id.item1 -> Toast.makeText(this@MainActivity, "item1", Toast.LENGTH_SHORT).show()
                R.id.item2 -> Toast.makeText(this@MainActivity, "item2", Toast.LENGTH_SHORT).show()
                R.id.item3 -> Toast.makeText(this@MainActivity, "item3", Toast.LENGTH_SHORT).show()
            }
            main.closeDrawer(GravityCompat.START)//при нажатии на любой item меню задвигается
            true
        }
    }
```
5. Добавление header - здесь обычно указывают номер телефона аккаунта, его фото (текст или картинка или еще что то(информация))
   
	5.1. res -> layout -> header
   
	5.2. Например:
```
<ConstraintLayout android:layout_width="match_parent" android:layout_height="150dp">
    <ImageView android:id="@+id/imageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
    <TextView android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Log In"/>
</ConstraintLayout>
```
5.3. добавим header в NavigationView
```
    <NavigationView
        android:id="@+id/navView"
        android:layout_gravity="start"
        app:menu="@menu/nav_menu"
        app:headerLayout="@layout/header"/> !!!!!!!!!
```
