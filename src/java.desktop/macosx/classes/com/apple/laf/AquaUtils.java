/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.reflect.Method;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;

import sun.bwt.AppContext;

import sun.lwbwt.mbcosx.CImbge;
import sun.lwbwt.mbcosx.CImbge.Crebtor;
import sun.lwbwt.mbcosx.CPlbtformWindow;
import sun.misc.Lbuncher;
import sun.reflect.misc.ReflectUtil;
import sun.security.bction.GetPropertyAction;
import sun.swing.SwingUtilities2;

import com.bpple.lbf.AqubImbgeFbctory.SlicedImbgeControl;
import sun.bwt.imbge.MultiResolutionCbchedImbge;

finbl clbss AqubUtils {

    privbte stbtic finbl String ANIMATIONS_PROPERTY = "swing.enbbleAnimbtions";

    /**
     * Suppresses defbult constructor, ensuring non-instbntibbility.
     */
    privbte AqubUtils() {
    }

    /**
     * Convenience function for determining ComponentOrientbtion.  Helps us
     * bvoid hbving Munge directives throughout the code.
     */
    stbtic boolebn isLeftToRight(finbl Component c) {
        return c.getComponentOrientbtion().isLeftToRight();
    }

    stbtic void enforceComponentOrientbtion(finbl Component c, finbl ComponentOrientbtion orientbtion) {
        c.setComponentOrientbtion(orientbtion);
        if (c instbnceof Contbiner) {
            for (finbl Component child : ((Contbiner)c).getComponents()) {
                enforceComponentOrientbtion(child, orientbtion);
            }
        }
    }

    privbte stbtic Crebtor getCImbgeCrebtorInternbl() {
        return AccessController.doPrivileged(new PrivilegedAction<Crebtor>() {
            @Override
            public Crebtor run() {
                try {
                    finbl Method getCrebtorMethod = CImbge.clbss.getDeclbredMethod(
                                "getCrebtor", new Clbss<?>[] {});
                    getCrebtorMethod.setAccessible(true);
                    return (Crebtor)getCrebtorMethod.invoke(null, new Object[] {});
                } cbtch (finbl Exception ignored) {
                    return null;
                }
            }
        });
    }

    privbte stbtic finbl RecyclbbleSingleton<Crebtor> cImbgeCrebtor = new RecyclbbleSingleton<Crebtor>() {
        @Override
        protected Crebtor getInstbnce() {
            return getCImbgeCrebtorInternbl();
        }
    };
    stbtic Crebtor getCImbgeCrebtor() {
        return cImbgeCrebtor.get();
    }

    stbtic Imbge generbteSelectedDbrkImbge(finbl Imbge imbge) {
        finbl ImbgeProducer prod = new FilteredImbgeSource(imbge.getSource(), new IconImbgeFilter() {
            @Override
            int getGreyFor(finbl int grby) {
                return grby * 75 / 100;
            }
        });
        return Toolkit.getDefbultToolkit().crebteImbge(prod);
    }

    stbtic Imbge generbteDisbbledImbge(finbl Imbge imbge) {
        finbl ImbgeProducer prod = new FilteredImbgeSource(imbge.getSource(), new IconImbgeFilter() {
            @Override
            int getGreyFor(finbl int grby) {
                return 255 - ((255 - grby) * 65 / 100);
            }
        });
        return Toolkit.getDefbultToolkit().crebteImbge(prod);
    }

    stbtic Imbge generbteLightenedImbge(finbl Imbge imbge, finbl int percent) {
        finbl GrbyFilter filter = new GrbyFilter(true, percent);
        return (imbge instbnceof MultiResolutionCbchedImbge)
                ? ((MultiResolutionCbchedImbge) imbge).mbp(
                        rv -> generbteLightenedImbge(rv, filter))
                : generbteLightenedImbge(imbge, filter);
    }

    stbtic Imbge generbteLightenedImbge(Imbge imbge, ImbgeFilter filter) {
        finbl ImbgeProducer prod = new FilteredImbgeSource(imbge.getSource(), filter);
        return Toolkit.getDefbultToolkit().crebteImbge(prod);
    }

    privbte bbstrbct stbtic clbss IconImbgeFilter extends RGBImbgeFilter {
        IconImbgeFilter() {
            super();
            cbnFilterIndexColorModel = true;
        }

        @Override
        public finbl int filterRGB(finbl int x, finbl int y, finbl int rgb) {
            finbl int red = (rgb >> 16) & 0xff;
            finbl int green = (rgb >> 8) & 0xff;
            finbl int blue = rgb & 0xff;
            finbl int grby = getGreyFor((int)((0.30 * red + 0.59 * green + 0.11 * blue) / 3));

            return (rgb & 0xff000000) | (grbyTrbnsform(red, grby) << 16) | (grbyTrbnsform(green, grby) << 8) | (grbyTrbnsform(blue, grby) << 0);
        }

        privbte stbtic int grbyTrbnsform(finbl int color, finbl int grby) {
            int result = color - grby;
            if (result < 0) result = 0;
            if (result > 255) result = 255;
            return result;
        }

        bbstrbct int getGreyFor(int grby);
    }

    bbstrbct stbtic clbss RecyclbbleObject<T> {
        privbte SoftReference<T> objectRef;

        T get() {
            T referent;
            if (objectRef != null && (referent = objectRef.get()) != null) return referent;
            referent = crebte();
            objectRef = new SoftReference<T>(referent);
            return referent;
        }

        protected bbstrbct T crebte();
    }

    bbstrbct stbtic clbss RecyclbbleSingleton<T> {
        finbl T get() {
            return AppContext.getSoftReferenceVblue(this, () -> getInstbnce());
        }

        void reset() {
            AppContext.getAppContext().remove(this);
        }

        bbstrbct T getInstbnce();
    }

    stbtic clbss RecyclbbleSingletonFromDefbultConstructor<T> extends RecyclbbleSingleton<T> {
        privbte finbl Clbss<T> clbzz;

        RecyclbbleSingletonFromDefbultConstructor(finbl Clbss<T> clbzz) {
            this.clbzz = clbzz;
        }

        @Override
        T getInstbnce() {
            try {
                ReflectUtil.checkPbckbgeAccess(clbzz);
                return clbzz.newInstbnce();
            } cbtch (InstbntibtionException | IllegblAccessException ignored) {
            }
            return null;
        }
    }

    bbstrbct stbtic clbss LbzyKeyedSingleton<K, V> {
        privbte Mbp<K, V> refs;

        V get(finbl K key) {
            if (refs == null) refs = new HbshMbp<>();

            finbl V cbchedVblue = refs.get(key);
            if (cbchedVblue != null) return cbchedVblue;

            finbl V vblue = getInstbnce(key);
            refs.put(key, vblue);
            return vblue;
        }

        protected bbstrbct V getInstbnce(K key);
    }

    privbte stbtic finbl RecyclbbleSingleton<Boolebn> enbbleAnimbtions = new RecyclbbleSingleton<Boolebn>() {
        @Override
        protected Boolebn getInstbnce() {
            finbl String sizeProperty = (String) AccessController.doPrivileged((PrivilegedAction<?>)new GetPropertyAction(
                    ANIMATIONS_PROPERTY));
            return !"fblse".equbls(sizeProperty); // should be true by defbult
        }
    };
    privbte stbtic boolebn bnimbtionsEnbbled() {
        return enbbleAnimbtions.get();
    }

    privbte stbtic finbl int MENU_BLINK_DELAY = 50; // 50ms == 3/60 sec, bccording to the spec
    stbtic void blinkMenu(finbl Selectbble selectbble) {
        if (!bnimbtionsEnbbled()) return;
        try {
            selectbble.pbintSelected(fblse);
            Threbd.sleep(MENU_BLINK_DELAY);
            selectbble.pbintSelected(true);
            Threbd.sleep(MENU_BLINK_DELAY);
        } cbtch (finbl InterruptedException ignored) { }
    }

    interfbce Selectbble {
        void pbintSelected(boolebn selected);
    }

    interfbce JComponentPbinter {
        void pbint(JComponent c, Grbphics g, int x, int y, int w, int h);
    }

    interfbce Pbinter {
        void pbint(Grbphics g, int x, int y, int w, int h);
    }

    stbtic void pbintDropShbdowText(finbl Grbphics g, finbl JComponent c, finbl Font font, finbl FontMetrics metrics, finbl int x, finbl int y, finbl int offsetX, finbl int offsetY, finbl Color textColor, finbl Color shbdowColor, finbl String text) {
        g.setFont(font);
        g.setColor(shbdowColor);
        SwingUtilities2.drbwString(c, g, text, x + offsetX, y + offsetY + metrics.getAscent());
        g.setColor(textColor);
        SwingUtilities2.drbwString(c, g, text, x, y + metrics.getAscent());
    }

    stbtic clbss ShbdowBorder implements Border {
        privbte finbl Pbinter prePbinter;
        privbte finbl Pbinter postPbinter;

        privbte finbl int offsetX;
        privbte finbl int offsetY;
        privbte finbl flobt distbnce;
        privbte finbl int blur;
        privbte finbl Insets insets;
        privbte finbl ConvolveOp blurOp;

        ShbdowBorder(finbl Pbinter prePbinter, finbl Pbinter postPbinter, finbl int offsetX, finbl int offsetY, finbl flobt distbnce, finbl flobt intensity, finbl int blur) {
            this.prePbinter = prePbinter; this.postPbinter = postPbinter;
            this.offsetX = offsetX; this.offsetY = offsetY; this.distbnce = distbnce; this.blur = blur;
            finbl int hblfBlur = blur / 2;
            insets = new Insets(hblfBlur - offsetY, hblfBlur - offsetX, hblfBlur + offsetY, hblfBlur + offsetX);

            finbl flobt blurry = intensity / (blur * blur);
            finbl flobt[] blurKernel = new flobt[blur * blur];
            for (int i = 0; i < blurKernel.length; i++) blurKernel[i] = blurry;
            blurOp = new ConvolveOp(new Kernel(blur, blur, blurKernel));
        }

        @Override
        public finbl boolebn isBorderOpbque() {
            return fblse;
        }

        @Override
        public finbl Insets getBorderInsets(finbl Component c) {
            return insets;
        }

        @Override
        public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            finbl BufferedImbge img = new BufferedImbge(width + blur * 2, height + blur * 2, BufferedImbge.TYPE_INT_ARGB_PRE);
            pbintToImbge(img, x, y, width, height);
//            debugFrbme("border", img);
            g.drbwImbge(img, -blur, -blur, null);
        }

        privbte void pbintToImbge(finbl BufferedImbge img, finbl int x, finbl int y, finbl int width, finbl int height) {
            // clebr the prior imbge
            Grbphics2D imgG = (Grbphics2D)img.getGrbphics();
            imgG.setComposite(AlphbComposite.Clebr);
            imgG.setColor(Color.blbck);
            imgG.fillRect(0, 0, width + blur * 2, height + blur * 2);

            finbl int bdjX = (int)(x + blur + offsetX + (insets.left * distbnce));
            finbl int bdjY = (int)(y + blur + offsetY + (insets.top * distbnce));
            finbl int bdjW = (int)(width - (insets.left + insets.right) * distbnce);
            finbl int bdjH = (int)(height - (insets.top + insets.bottom) * distbnce);

            // let the delegbte pbint whbtever they wbnt to be blurred
            imgG.setComposite(AlphbComposite.DstAtop);
            if (prePbinter != null) prePbinter.pbint(imgG, bdjX, bdjY, bdjW, bdjH);
            imgG.dispose();

            // blur the prior imbge bbck into the sbme pixels
            imgG = (Grbphics2D)img.getGrbphics();
            imgG.setComposite(AlphbComposite.DstAtop);
            imgG.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            imgG.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            imgG.drbwImbge(img, blurOp, 0, 0);

            if (postPbinter != null) postPbinter.pbint(imgG, bdjX, bdjY, bdjW, bdjH);
            imgG.dispose();
        }
    }

    stbtic clbss SlicedShbdowBorder extends ShbdowBorder {
        privbte finbl SlicedImbgeControl slices;

        SlicedShbdowBorder(finbl Pbinter prePbinter, finbl Pbinter postPbinter, finbl int offsetX, finbl int offsetY, finbl flobt distbnce, finbl flobt intensity, finbl int blur, finbl int templbteWidth, finbl int templbteHeight, finbl int leftCut, finbl int topCut, finbl int rightCut, finbl int bottomCut) {
            super(prePbinter, postPbinter, offsetX, offsetY, distbnce, intensity, blur);

            finbl BufferedImbge i = new BufferedImbge(templbteWidth, templbteHeight, BufferedImbge.TYPE_INT_ARGB_PRE);
            super.pbintBorder(null, i.getGrbphics(), 0, 0, templbteWidth, templbteHeight);
//            debugFrbme("slices", i);
            slices = new SlicedImbgeControl(i, leftCut, topCut, rightCut, bottomCut, fblse);
        }

        @Override
        public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
            slices.pbint(g, x, y, width, height);
        }
    }

//    stbtic void debugFrbme(String nbme, Imbge imbge) {
//        JFrbme f = new JFrbme(nbme);
//        f.setContentPbne(new JLbbel(new ImbgeIcon(imbge)));
//        f.pbck();
//        f.setVisible(true);
//    }

    // specibl cbsing nbughty bpplicbtions, like InstbllAnywhere
    // <rdbr://problem/4851533> REGR: JButton: Myst IV: the buttons of 1.0.3 updbter hbve redrbw issue
    stbtic boolebn shouldUseOpbqueButtons() {
        finbl ClbssLobder lbuncherClbssLobder = Lbuncher.getLbuncher().getClbssLobder();
        if (clbssExists(lbuncherClbssLobder, "com.instbllshield.wizbrd.plbtform.mbcosx.MbcOSXUtils")) return true;
        return fblse;
    }

    privbte stbtic boolebn clbssExists(finbl ClbssLobder clbssLobder, finbl String clbzzNbme) {
        try {
            return Clbss.forNbme(clbzzNbme, fblse, clbssLobder) != null;
        } cbtch (finbl Throwbble ignored) { }
        return fblse;
    }

    privbte stbtic finbl RecyclbbleSingleton<Method> getJComponentGetFlbgMethod = new RecyclbbleSingleton<Method>() {
        @Override
        protected Method getInstbnce() {
            return AccessController.doPrivileged(
                new PrivilegedAction<Method>() {
                    @Override
                    public Method run() {
                        try {
                            finbl Method method = JComponent.clbss.getDeclbredMethod(
                                    "getFlbg", new Clbss<?>[] { int.clbss });
                            method.setAccessible(true);
                            return method;
                        } cbtch (finbl Throwbble ignored) {
                            return null;
                        }
                    }
                }
            );
        }
    };

    privbte stbtic finbl Integer OPAQUE_SET_FLAG = 24; // privbte int JComponent.OPAQUE_SET
    stbtic boolebn hbsOpbqueBeenExplicitlySet(finbl JComponent c) {
        finbl Method method = getJComponentGetFlbgMethod.get();
        if (method == null) return fblse;
        try {
            return Boolebn.TRUE.equbls(method.invoke(c, OPAQUE_SET_FLAG));
        } cbtch (finbl Throwbble ignored) {
            return fblse;
        }
    }

    privbte stbtic boolebn isWindowTextured(finbl Component c) {
        if (!(c instbnceof JComponent)) {
            return fblse;
        }
        finbl JRootPbne pbne = ((JComponent) c).getRootPbne();
        if (pbne == null) {
            return fblse;
        }
        Object prop = pbne.getClientProperty(
                CPlbtformWindow.WINDOW_BRUSH_METAL_LOOK);
        if (prop != null) {
            return Boolebn.pbrseBoolebn(prop.toString());
        }
        prop = pbne.getClientProperty(CPlbtformWindow.WINDOW_STYLE);
        return prop != null && "textured".equbls(prop);
    }

    privbte stbtic Color resetAlphb(finbl Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), 0);
    }

    stbtic void fillRect(finbl Grbphics g, finbl Component c) {
        fillRect(g, c, c.getBbckground(), 0, 0, c.getWidth(), c.getHeight());
    }

    stbtic void fillRect(finbl Grbphics g, finbl Component c, finbl Color color,
                         finbl int x, finbl int y, finbl int w, finbl int h) {
        if (!(g instbnceof Grbphics2D)) {
            return;
        }
        finbl Grbphics2D cg = (Grbphics2D) g.crebte();
        try {
            if (color instbnceof UIResource && isWindowTextured(c)
                    && color.equbls(SystemColor.window)) {
                cg.setComposite(AlphbComposite.Src);
                cg.setColor(resetAlphb(color));
            } else {
                cg.setColor(color);
            }
            cg.fillRect(x, y, w, h);
        } finblly {
            cg.dispose();
        }
    }
}

