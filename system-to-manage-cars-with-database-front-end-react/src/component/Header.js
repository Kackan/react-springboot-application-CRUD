import React from 'react'
import { Link } from 'react-router-dom';

function Header() {


    return (
        <div>
            <header>
                <nav className="navbar navbar-dark bg-dark">
                    <span className="navbar-brand">Cars management system</span>

                    <ul className="list-group list-group-horizontal">
                        <Link to='/create'>
                            <li className="btn btn-outline-success">Create car</li>
                        </Link>
                        <Link to='/'>
                            <li className="btn btn-outline-info">Cars</li>  
                        </Link>


                    </ul>


                </nav>
            </header>
        </div>
    )
}

export default Header
