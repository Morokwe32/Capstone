import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import AboutUs from '@/views/AboutUs.vue';
import Bookings from '@/views/Bookings.vue';
import ContactUs from '@/views/ContactUs.vue';
import Faqs from '@/views/Faqs.vue';
import HelpDesk from '@/views/HelpDesk.vue';
import Login from '@/views/Login.vue';
import Register from '@/views/Register.vue';
import Signout from '@/views/Signout.vue';
import UserProfile from '@/views/UserProfile.vue';
import AdminHome from '@/views/Admin/AdminHome.vue';
import UserManagement from '@/views/Admin/User/UserManagement.vue';
import BookingManagement from '@/views/Admin/Booking/BookingManagement.vue';
import RoomManagement from '@/views/Admin/Room/RoomManagment.vue';
import CreateUser from '@/views/Admin/User/CreateUser.vue';
import CreateRoom from '@/views/Admin/Room/CreateRoom.vue';
import CreateBooking from '@/views/Admin/Booking/CreateBooking.vue';
import UpdateUser from '@/views/Admin/User/UpdateUser.vue';


const routes = [
    { path: '/', name: 'Home', component: Home },
    { path: '/about-us', name: 'AboutUs', component: AboutUs },
    { path: '/bookings', name: 'Bookings', component: Bookings },
    { path: '/contact-us', name: 'ContactUs', component: ContactUs },
    { path: '/faqs', name: 'Faqs', component: Faqs },
    { path: '/help-desk', name: 'HelpDesk', component: HelpDesk },
    { path: '/login', name: 'Login', component: Login },
    { path: '/register', name: 'Register', component: Register },
    { path: '/signout', name: 'Signout', component: Signout },
    { path: '/profile/:userId', name: 'UserProfile', component: UserProfile, props: true },
    { path: '/admin-home', name: 'AdminHome', component: AdminHome },
    { path: '/admin/user-management', name: 'UserManagement', component: UserManagement },
    { path: '/admin/booking-management', name: 'BookingManagement', component: BookingManagement },
    { path: '/admin/room-management', name: 'RoomManagement', component: RoomManagement },
    { path: '/admin/users/create', name: 'CreateUser', component: CreateUser },
    { path: '/admin/rooms/create', name: 'CreateRoom', component: CreateRoom },
    { path: '/admin/bookings/create', name: 'CreateBooking', component: CreateBooking },
    { path: '/user-profile', component: UserProfile },
    { path: '/admin/users/update/:id', name: 'UpdateUser', component: UpdateUser, props: true },
    { path: '/user/profile/:userId', component: UserProfile, name: 'UserProfile' }

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
