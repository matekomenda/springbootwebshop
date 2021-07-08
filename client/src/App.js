import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home/Home";
import Footer from "./components/Footer";
import Cart from "./pages/Cart/Cart";
import Product from "./pages/Product/Product";
import WishList from "./pages/WishList/WishList";
import Login from './pages/Auth/Login';
import Register from './pages/Auth/Register';
import AddProduct from "./pages/Product/AddProduct";
import ProductPage from "./pages/Product/ProductPage";
import Checkout from "./pages/Checkout/Checkout";

function App() {

  return (
    <Router>
      <Navbar />
      <Switch>
        <Route path="/" component={Home} exact />
        <Route path="/products" component={Product} exact />
        <Route path="/product" component={ProductPage} exact />
        <Route path="/cart" component={Cart} exact />
        <Route path="/checkout" component={Checkout} exact />
        <Route path="/wishlist" component={WishList} exact />
        <Route path="/register" component={Register} exact />
        <Route path="/login" component={Login} exact />
        <Route path="/admin/addProduct" component={AddProduct} exact />
      </Switch>
      <Footer />
    </Router>
  );
}

export default App;
