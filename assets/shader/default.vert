attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

varying vec4 v_color;
varying vec2 v_texCoords;

uniform mat4 u_projTrans;

void main()
{
    v_color = a_color;
    v_color.a = v_color.a * (255.0 / 254.0);
    v_texCoords = a_texCoord0;
    gl_Position =  u_projTrans * a_position;
}
