# WeatherApp
## How to use this app? 
###### when you run it, you should type your city name on the search bar. After that just submit your search by pressing the search icon on the keyboard.

## Waht I did to migrate from Dagger2 to Hilt? see steps below:
###### 1. Remove Dagger Dependencies and add Hilt dependencies (find dependencies in build.gradle).
###### 2. Extend application class from Application or MultidexApplication instead of DaggerApplication.
###### 3. Add 'HiltAndroidApp' annotation to application class (look at WeatherApp class).
###### 4. Remove all component and subcomponent class from project. also remove appComponent form application class.
###### 5. Add 'InstallIn' annotation in all module classes.
###### 6. Remove all providing methods for activities, fragments, views, services and broadcast receivers, instead add 'AndroidEntryPoint' annotation to their class.
###### 7. Extend activities from AppCompatActivity instead of DaggerActivity, and extend fragments from Fragment intead of DaggerFragmnt.
###### 8. Remove view model factory and all methods providing view models in module classes.
###### 9. Add 'HiltViewModel' annotation to view model classes.
###### 10. Use this pattern to declare view model in activity or fragment
###### `private val viewmodel: CustomViewModel by viewModels()`
###### CustomViewModel is your desire ViewModel class.
###### 11. If you have shared view model. use below pattern to declare that (just in fragments)
###### `private val viewModel: SharedViewModel by activityViewModels()`
###### 12. If you use providing or binding in module classes to provide your project classes, stop it! Use 'Inject' annotation in thier constroctor instead.
###### 13. Using qualifiers instead of using 'Named' annotation.
###### 14. Define scope for your classes which are not android component, also define scope for all module classes and binding/providing methods. Otherwisw you will have leak!

