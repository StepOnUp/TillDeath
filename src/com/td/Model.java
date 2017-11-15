package com.td;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Model {
    
    private int drawCount;
    private int vId;
    private int tId;
    
    public Model(float[] vertices, float[] textureCoords) {
        drawCount = vertices.length / 3;

        vId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);
        
        tId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(textureCoords), GL_STATIC_DRAW);
    
        glBindBuffer(GL_ARRAY_BUFFER, 0);
     }
     
     public void render() {
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glVertexPointer(3, GL_FLOAT, 0, 0);
        
        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glTexCoordPointer(2, GL_FLOAT, 0, 0 );
        
        glDrawArrays(GL_TRIANGLES, 0, drawCount);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
     }
     
     private FloatBuffer createBuffer(float[] data) {
         FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
         buffer.put(data);
         buffer.flip();
         
         return buffer;
     }
}
