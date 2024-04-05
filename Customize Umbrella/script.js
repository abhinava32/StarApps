// script.js
var flag = false;

function changeSVGFillColor(color) {
  document.getElementById('mySVG').style.fill = color;
}

function rotateContinuously() {
  const duration = 3000; // Duration in milliseconds
  const svgElement = document.getElementById('mySVG');
  let angle = 0;
  const startTime = Date.now();

  const rotateInterval = setInterval(() => {
      const elapsedTime = Date.now() - startTime;
      angle = (elapsedTime / duration) * 360; // Calculate rotation angle based on elapsed time
      svgElement.setAttribute('transform', `rotate(${angle})`);

      if (elapsedTime >= duration) {
          clearInterval(rotateInterval);
          
          document.getElementById('mySVG').style.display = 'none';
          document.getElementById('umbrella').style.display = 'block';
          document.getElementById('logo-preview').style.display = 'block'; // Stop rotating after specified duration
      }
  }, 16); // Run the rotation function approximately every 16 milliseconds
}

function changeColor(color,backgroundColor) {
  changeSVGFillColor(backgroundColor);
  document.getElementById('myLabel').style.backgroundColor=backgroundColor;

  if(flag === true ){
    document.getElementById('umbrella').style.display = 'none';
    document.getElementById('logo-preview').style.display = 'none';
    
    const svgElement = document.getElementById('mySVG');
    svgElement.style.display = 'block';
    changeSVGFillColor(backgroundColor);

    rotateContinuously();
    console.log("flag is true");
  }
    document.body.style.transition = "background-image 2s ease"; 
    document.body.style.backgroundImage = `linear-gradient(#e2dfd6 70%,${backgroundColor})`;
    document.getElementById('umbrella').src = `Static/${color} umbrella.png`;
  }
  


function previewLogo(event) {
  
  flag = true;
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('umbrella').style.display = 'none';
      document.getElementById('logo-preview').style.display = 'none';
      const svgElement = document.getElementById('mySVG');
      svgElement.style.display = 'block';

    rotateContinuously();
    console.log("flag is true");
      
      document.getElementById('logo-preview').src = e.target.result;
    }
    reader.readAsDataURL(file);
  }
}


  