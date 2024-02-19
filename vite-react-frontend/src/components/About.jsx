import '../css/About.css';

function About() {
    return ( 
        <>
        <div className='color'>
        {/* <h1 className="edit">About Us</h1> */}
        <header>
    <h2>About Us</h2>
  </header>
  <main>
    <section class="about-section">
      <h3>Our Story</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam condimentum faucibus ex, eget fringilla felis fermentum ut. Nullam at elit ut urna euismod malesuada id eget libero. Duis tincidunt, mauris nec dignissim venenatis, dui dolor pharetra libero, eu auctor lectus elit id erat. Ut faucibus ex vel metus lacinia mollis.</p>
    </section>
    <section class="about-section">
      <h3>Our Mission</h3>
      <p>Integer vitae arcu purus. Nullam dictum vitae magna at tincidunt. Quisque auctor, odio nec venenatis convallis, odio libero vehicula mi, ut varius eros arcu ut ipsum. Integer a sapien ut lorem faucibus efficitur. Ut nec urna dolor.</p>
    </section>
    <section class="about-section">
      <h3>Our Team</h3>
      <ul>
        <li>Chaitanya Bhawar - CEO</li>
        <li>Chetan Arote - CFO</li>
        <li>Sarvesh Kadwe - CTO</li>
        <li>Nilesh Katkar - CTO</li>
      </ul>
    </section>
  </main>
  <footer>
    
  </footer>
        </div>
        </>
     );
}

export default About;