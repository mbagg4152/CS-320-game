main();

function main() {
    const canvas = document.querySelector('#glcanvas');
    const gl = canvas.getContext('webgl');

    if (!gl) {
        alert('Unable to initialize WebGL. Your browser or machine may not support it.');
        return;
    }

    gl.clearColor(0.0, 0.0, 0.0, 1.0); // Set clear color to black, fully opaque
    gl.clear(gl.COLOR_BUFFER_BIT); // Clear the color buffer with specified clear color
    var vertices = [0.5, 0.5, 0.1, -0.5, 0.5, -0.5];
    var vertex_buffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, vertex_buffer);
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);// Pass the vertices data to the buffer
    gl.bindBuffer(gl.ARRAY_BUFFER, null); // Unbind the buffer

    // Vertex shader source code
    var vertCode =
        'attribute vec2 coordinates;' +
        'void main(void) {' + ' gl_Position = vec4(coordinates,0.0, 1.0);' + '}';
    var vertShader = gl.createShader(gl.VERTEX_SHADER);//Create a vertex shader object
    gl.shaderSource(vertShader, vertCode);//Attach vertex shader source code
    gl.compileShader(vertShader); //Compile the vertex shader
    var fragCode = 'void main(void) {' + 'gl_FragColor = vec4(0.0, 0.0, 0.0, 0.1);' + '}'; //Fragment shader source code
    var fragShader = gl.createShader(gl.FRAGMENT_SHADER);// Create fragment shader object
    gl.shaderSource(fragShader, fragCode);// Attach fragment shader source code
    gl.compileShader(fragShader);// Compile the fragment shader
    var shaderProgram = gl.createProgram();// Create a shader program object to store combined shader program
    gl.attachShader(shaderProgram, vertShader);// Attach a vertex shader
    gl.attachShader(shaderProgram, fragShader);// Attach a fragment shader
    gl.linkProgram(shaderProgram);// Link both programs
    gl.useProgram(shaderProgram);// Use the combined shader program object

    gl.bindBuffer(gl.ARRAY_BUFFER, vertex_buffer);//Bind vertex buffer object
    var coord = gl.getAttribLocation(shaderProgram, "coordinates"); //Get the attribute location
    gl.vertexAttribPointer(coord, 2, gl.FLOAT, false, 0, 0);//point an attribute to the currently bound VBO
    gl.enableVertexAttribArray(coord); //Enable the attribute

    gl.clearColor(0.5, 0.5, 0.5, 0.9);// Clear the canvas
    gl.enable(gl.DEPTH_TEST);// Enable the depth test
    gl.clear(gl.COLOR_BUFFER_BIT);// Clear the color buffer bit
    gl.viewport(0, 0, canvas.width, canvas.height); // Set the view port
    gl.drawArrays(gl.TRIANGLES, 0, 3);// Draw the triangle
}