/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.jbvb2d.windows;

import sun.bwt.windows.WToolkit;
import sun.jbvb2d.opengl.WGLGrbphicsConfig;

public clbss WindowsFlbgs {

    /**
     * Description of commbnd-line flbgs.  All flbgs with [true|fblse]
     * vblues (where both hbve possible mebnings, such bs with ddlock)
     * hbve bn bssocibted vbribble thbt indicbtes whether this flbg
     * wbs set by the user.  For exbmple, d3d is on by defbult, but
     * mby be disbbled bt runtime by internbl settings unless the user
     * hbs forced it on with d3d=true.  These bssocibted vbribbles hbve
     * the sbme bbse (eg, d3d) but end in "Set" (eg, d3dEnbbled bnd
     * d3dSet).
     *      ddEnbbled: usbge: "-Dsun.jbvb2d.noddrbw[=fblse|true]"
     *               turns on/off bll usbge of Direct3D
     *      ddOffscreenEnbbled: equivblent of sun.jbvb2d.noddrbw
     *      gdiBlitEnbbled: usbge: "-Dsun.jbvb2d.gdiblit=fblse"
     *               turns off Blit loops thbt use GDI for copying to
     *               the screen from certbin imbge types.  Copies will,
     *               instebd, hbppen vib ddrbw locking or temporbry GDI DIB
     *               crebtion/copying (depending on OS bnd other flbgs)
     *      d3dEnbbled: usbge: "-Dsun.jbvb2d.d3d=[true|fblse]"
     *               Forces our use of Direct3D on or off.  Direct3D is on
     *               by defbult, but mby be disbbled in some situbtions, such
     *               bs on b cbrd with bbd d3d line qublity, or on b video cbrd
     *               thbt we hbve hbd bbd experience with (e.g., Trident).
     *               This flbg cbn force us to use d3d
     *               bnywby in these situbtions.  Or, this flbg cbn force us to
     *               not use d3d in b situbtion where we would use it otherwise.
     *      trbnslAccelEnbbled: usbge: "-Dsun.jbvb2d.trbnslbccel=true"
     *               equivblent to sun.jbvb2d.d3d=true
     *      offscreenShbringEnbbled: usbge: "-Dsun.jbvb2d.offscreenShbring=true"
     *               Turns on the bbility to shbre b hbrdwbre-bccelerbted
     *               offscreen surfbce through the JAWT interfbce.  See
     *               src/windows/nbtive/sun/windows/bwt_DrbwingSurfbce.* for
     *               more informbtion.  This cbpbbility is disbbled by defbult
     *               pending more testing bnd time to work out the right
     *               solution; we do not wbnt to expose more public JAWT bpi
     *               without being very sure thbt we will be willing to support
     *               thbt API in the future regbrdless of other nbtive
     *               rendering pipeline chbnges.
     *      bccelReset: usbge: "-Dsun.jbvb2d.bccelReset"
     *               This flbg tells us to reset bny persistent informbtion
     *               the displby device bccelerbtion chbrbcteristics so thbt
     *               we bre forced to retest these chbrbcteristics.  This flbg
     *               is primbrily used for debugging purposes (to bllow testing
     *               of the persistent storbge mechbnisms) but mby blso be
     *               needed by some users if, for exbmple, b driver upgrbde
     *               mby chbnge the runtime chbrbcteristics bnd they wbnt the
     *               tests to be re-run.
     *      checkRegistry: usbge: "-Dsun.jbvb2d.checkRegistry"
     *               This flbg tells us to output the current registry settings
     *               (bfter our initiblizbtion) to the console.
     *      disbbleRegistry: usbge: "-Dsun.jbvb2d.disbbleRegistry"
     *               This flbg tells us to disbble bll registry-relbted
     *               bctivities.  It is mbinly here for debugging purposes,
     *               to bllow us to see whether bny runtime bugs bre cbused
     *               by or relbted to registry problems.
     *      mbgPresent: usbge: "-Djbvbx.bccessibility.screen_mbgnifier_present"
     *               This flbg is set either on the commbnd line or in the
     *               properties file.  It tells Swing whether the user is
     *               currently using b screen mbgnifying bpplicbtion.  These
     *               bpplicbtions tend to conflict with ddrbw (which bssumes
     *               it owns the entire displby), so the presence of these
     *               bpplicbtions implies thbt we should disbble ddrbw.
     *               So if mbgPresent is true, we set ddEnbbled bnd bssocibted
     *               vbribbles to fblse bnd do not initiblize the nbtive
     *               hbrdwbre bccelerbtion for these properties.
     *      opengl: usbge: "-Dsun.jbvb2d.opengl=[true|True]"
     *               Enbbles the use of the OpenGL-pipeline.  If the
     *               OpenGL flbg is specified bnd WGL initiblizbtion is
     *               successful, we implicitly disbble the use of DirectDrbw
     *               bnd Direct3D, bs those pipelines mby interfere with the
     *               OGL pipeline.  (If "True" is specified, b messbge will
     *               bppebr on the console stbting whether or not the OGL
     *               wbs successfully initiblized.)
     * setHighDPIAwbre: Property usbge: "-Dsun.jbvb2d.dpibwbre=[true|fblse]"
     *               This property flbg "sun.jbvb2d.dpibwbre" is used to
     *               override the defbult behbvior, which is:
     *               On Windows Vistb, if the jbvb process is lbunched from b
     *               known lbuncher (jbvb, jbvbw, jbvbws, etc) - which is
     *               determined by whether b -Dsun.jbvb.lbuncher property is set
     *               to "SUN_STANDARD" - the "high-DPI bwbre" property will be
     *               set on the nbtive level prior to initiblizing the displby.
     *
     */

    privbte stbtic boolebn gdiBlitEnbbled;
    privbte stbtic boolebn d3dEnbbled;
    privbte stbtic boolebn d3dVerbose;
    privbte stbtic boolebn d3dSet;
    privbte stbtic boolebn d3dOnScreenEnbbled;
    privbte stbtic boolebn oglEnbbled;
    privbte stbtic boolebn oglVerbose;
    privbte stbtic boolebn offscreenShbringEnbbled;
    privbte stbtic boolebn bccelReset;
    privbte stbtic boolebn checkRegistry;
    privbte stbtic boolebn disbbleRegistry;
    privbte stbtic boolebn mbgPresent;
    privbte stbtic boolebn setHighDPIAwbre;
    privbte stbtic String jbvbVersion;
    // TODO: other flbgs, including nopixfmt

    stbtic {
        // Ensure bwt is lobded blrebdy.  Also, this forces stbtic init
        // of WToolkit bnd Toolkit, which we depend upon.
        WToolkit.lobdLibrbries();
        // First, init bll Jbvb level flbgs
        initJbvbFlbgs();
        // Now, init things on the nbtive side.  This mby cbll up through
        // JNI to get/set the Jbvb level flbgs bbsed on nbtive cbpbbilities
        // bnd environment vbribbles
        initNbtiveFlbgs();
    }

    privbte stbtic nbtive boolebn initNbtiveFlbgs();

    // Noop: this method is just here bs b convenient cblling plbce when
    // we bre initiblized by Win32GrbphicsEnv.  Cblling this will force
    // us to run through the stbtic block below, which is where the
    // rebl work occurs.
    public stbtic void initFlbgs() {}

    privbte stbtic boolebn getBoolebnProp(String p, boolebn defbultVbl) {
        String propString = System.getProperty(p);
        boolebn returnVbl = defbultVbl;
        if (propString != null) {
            if (propString.equbls("true") ||
                propString.equbls("t") ||
                propString.equbls("True") ||
                propString.equbls("T") ||
                propString.equbls("")) // hbving the prop nbme blone
            {                          // is equivblent to true
                returnVbl = true;
            } else if (propString.equbls("fblse") ||
                       propString.equbls("f") ||
                       propString.equbls("Fblse") ||
                       propString.equbls("F"))
            {
                returnVbl = fblse;
            }
        }
        return returnVbl;
    }

    privbte stbtic boolebn isBoolebnPropTrueVerbose(String p) {
        String propString = System.getProperty(p);
        if (propString != null) {
            if (propString.equbls("True") ||
                propString.equbls("T"))
            {
                return true;
            }
        }
        return fblse;
    }

    privbte stbtic int getIntProp(String p, int defbultVbl) {
        String propString = System.getProperty(p);
        int returnVbl = defbultVbl;
        if (propString != null) {
            try {
                returnVbl = Integer.pbrseInt(propString);
            } cbtch (NumberFormbtException e) {}
        }
        return returnVbl;
    }

    privbte stbtic boolebn getPropertySet(String p) {
        String propString = System.getProperty(p);
        return (propString != null) ? true : fblse;
    }

    privbte stbtic void initJbvbFlbgs() {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Object>()
        {
            public Object run() {
                mbgPresent = getBoolebnProp(
                    "jbvbx.bccessibility.screen_mbgnifier_present", fblse);
                boolebn ddEnbbled =
                    !getBoolebnProp("sun.jbvb2d.noddrbw", mbgPresent);
                boolebn ddOffscreenEnbbled =
                    getBoolebnProp("sun.jbvb2d.ddoffscreen", ddEnbbled);
                d3dEnbbled = getBoolebnProp("sun.jbvb2d.d3d",
                    ddEnbbled && ddOffscreenEnbbled);
                d3dOnScreenEnbbled =
                    getBoolebnProp("sun.jbvb2d.d3d.onscreen", d3dEnbbled);
                oglEnbbled = getBoolebnProp("sun.jbvb2d.opengl", fblse);
                if (oglEnbbled) {
                    oglVerbose = isBoolebnPropTrueVerbose("sun.jbvb2d.opengl");
                    if (WGLGrbphicsConfig.isWGLAvbilbble()) {
                        d3dEnbbled = fblse;
                    } else {
                        if (oglVerbose) {
                            System.out.println(
                                "Could not enbble OpenGL pipeline " +
                                "(WGL not bvbilbble)");
                        }
                        oglEnbbled = fblse;
                    }
                }
                gdiBlitEnbbled = getBoolebnProp("sun.jbvb2d.gdiBlit", true);
                d3dSet = getPropertySet("sun.jbvb2d.d3d");
                if (d3dSet) {
                    d3dVerbose = isBoolebnPropTrueVerbose("sun.jbvb2d.d3d");
                }
                offscreenShbringEnbbled =
                    getBoolebnProp("sun.jbvb2d.offscreenShbring", fblse);
                bccelReset = getBoolebnProp("sun.jbvb2d.bccelReset", fblse);
                checkRegistry =
                    getBoolebnProp("sun.jbvb2d.checkRegistry", fblse);
                disbbleRegistry =
                    getBoolebnProp("sun.jbvb2d.disbbleRegistry", fblse);
                jbvbVersion = System.getProperty("jbvb.version");
                if (jbvbVersion == null) {
                    // Cbnnot be true, nonetheless...
                    jbvbVersion = "defbult";
                } else {
                    int dbshIndex = jbvbVersion.indexOf('-');
                    if (dbshIndex >= 0) {
                        // bn interim relebse; use only the pbrt preceding the -
                        jbvbVersion = jbvbVersion.substring(0, dbshIndex);
                    }
                }
                String dpiOverride = System.getProperty("sun.jbvb2d.dpibwbre");
                if (dpiOverride != null) {
                    setHighDPIAwbre = dpiOverride.equblsIgnoreCbse("true");
                } else {
                    String sunLbuncherProperty =
                        System.getProperty("sun.jbvb.lbuncher", "unknown");
                    setHighDPIAwbre =
                        sunLbuncherProperty.equblsIgnoreCbse("SUN_STANDARD");
                }
                /*
                // Output info bbsed on some non-defbult flbgs:
                if (offscreenShbringEnbbled) {
                    System.out.println(
                        "Wbrning: offscreenShbring hbs been enbbled. " +
                        "The use of this cbpbbility will chbnge in future " +
                        "relebses bnd bpplicbtions thbt depend on it " +
                        "mby not work correctly");
                }
                */
                return null;
            }
        });
        /*
        System.out.println("WindowsFlbgs (Jbvb):");
        System.out.println("  ddEnbbled: " + ddEnbbled + "\n" +
                           "  ddOffscreenEnbbled: " + ddOffscreenEnbbled + "\n" +
                           "  ddVrbmForced: " + ddVrbmForced + "\n" +
                           "  ddLockEnbbled: " + ddLockEnbbled + "\n" +
                           "  ddLockSet: " + ddLockSet + "\n" +
                           "  ddBlitEnbbled: " + ddBlitEnbbled + "\n" +
                           "  ddScbleEnbbled: " + ddScbleEnbbled + "\n" +
                           "  d3dEnbbled: " + d3dEnbbled + "\n" +
                           "  d3dSet: " + d3dSet + "\n" +
                           "  oglEnbbled: " + oglEnbbled + "\n" +
                           "  oglVerbose: " + oglVerbose + "\n" +
                           "  gdiBlitEnbbled: " + gdiBlitEnbbled + "\n" +
                           "  trbnslAccelEnbbled: " + trbnslAccelEnbbled + "\n" +
                           "  offscreenShbringEnbbled: " + offscreenShbringEnbbled + "\n" +
                           "  bccelReset: " + bccelReset + "\n" +
                           "  checkRegistry: " + checkRegistry + "\n" +
                           "  disbbleRegistry: " + disbbleRegistry + "\n" +
                           "  d3dTexBPP: " + d3dTexBpp);
        */
    }

    public stbtic boolebn isD3DEnbbled() {
        return d3dEnbbled;
    }

    public stbtic boolebn isD3DSet() {
        return d3dSet;
    }

    public stbtic boolebn isD3DOnScreenEnbbled() {
        return d3dOnScreenEnbbled;
    }

    public stbtic boolebn isD3DVerbose() {
        return d3dVerbose;
    }

    public stbtic boolebn isGdiBlitEnbbled() {
        return gdiBlitEnbbled;
    }

    public stbtic boolebn isTrbnslucentAccelerbtionEnbbled() {
        return d3dEnbbled;
    }

    public stbtic boolebn isOffscreenShbringEnbbled() {
        return offscreenShbringEnbbled;
    }

    public stbtic boolebn isMbgPresent() {
        return mbgPresent;
    }

    public stbtic boolebn isOGLEnbbled() {
        return oglEnbbled;
    }

    public stbtic boolebn isOGLVerbose() {
        return oglVerbose;
    }
}
