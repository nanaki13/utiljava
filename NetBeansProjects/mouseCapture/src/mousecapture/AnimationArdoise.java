/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

/**
 *
 * @author jonathan
 */
public enum AnimationArdoise {
    STATIC(new StaticAnimation()),DYNAMIC(new DynamicAnimation());
    private final Animation anim;
    AnimationArdoise(Animation a){
        this.anim = a;
    }

    public Animation getAnim() {
        return anim;
    }
    
    
}
