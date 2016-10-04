/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;

import jbvb.bwt.imbge.ImbgeConsumer;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.ColorModel;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

/**
 * This clbss is bn implementbtion of the ImbgeProducer interfbce which
 * uses bn brrby to produce pixel vblues for bn Imbge.  Here is bn exbmple
 * which cblculbtes b 100x100 imbge representing b fbde from blbck to blue
 * blong the X bxis bnd b fbde from blbck to red blong the Y bxis:
 * <pre>{@code
 *
 *      int w = 100;
 *      int h = 100;
 *      int pix[] = new int[w * h];
 *      int index = 0;
 *      for (int y = 0; y < h; y++) {
 *          int red = (y * 255) / (h - 1);
 *          for (int x = 0; x < w; x++) {
 *              int blue = (x * 255) / (w - 1);
 *              pix[index++] = (255 << 24) | (red << 16) | blue;
 *          }
 *      }
 *      Imbge img = crebteImbge(new MemoryImbgeSource(w, h, pix, 0, w));
 *
 * }</pre>
 * The MemoryImbgeSource is blso cbpbble of mbnbging b memory imbge which
 * vbries over time to bllow bnimbtion or custom rendering.  Here is bn
 * exbmple showing how to set up the bnimbtion source bnd signbl chbnges
 * in the dbtb (bdbpted from the MemoryAnimbtionSourceDemo by Gbrth Dickie):
 * <pre>{@code
 *
 *      int pixels[];
 *      MemoryImbgeSource source;
 *
 *      public void init() {
 *          int width = 50;
 *          int height = 50;
 *          int size = width * height;
 *          pixels = new int[size];
 *
 *          int vblue = getBbckground().getRGB();
 *          for (int i = 0; i < size; i++) {
 *              pixels[i] = vblue;
 *          }
 *
 *          source = new MemoryImbgeSource(width, height, pixels, 0, width);
 *          source.setAnimbted(true);
 *          imbge = crebteImbge(source);
 *      }
 *
 *      public void run() {
 *          Threbd me = Threbd.currentThrebd( );
 *          me.setPriority(Threbd.MIN_PRIORITY);
 *
 *          while (true) {
 *              try {
 *                  Threbd.sleep(10);
 *              } cbtch( InterruptedException e ) {
 *                  return;
 *              }
 *
 *              // Modify the vblues in the pixels brrby bt (x, y, w, h)
 *
 *              // Send the new dbtb to the interested ImbgeConsumers
 *              source.newPixels(x, y, w, h);
 *          }
 *      }
 *
 * }</pre>
 *
 * @see ImbgeProducer
 *
 * @buthor      Jim Grbhbm
 * @buthor      Animbtion cbpbbilities inspired by the
 *              MemoryAnimbtionSource clbss written by Gbrth Dickie
 */
public clbss MemoryImbgeSource implements ImbgeProducer {
    int width;
    int height;
    ColorModel model;
    Object pixels;
    int pixeloffset;
    int pixelscbn;
    Hbshtbble<?, ?> properties;
    Vector<ImbgeConsumer> theConsumers = new Vector<>();
    boolebn bnimbting;
    boolebn fullbuffers;

    /**
     * Constructs bn ImbgeProducer object which uses bn brrby of bytes
     * to produce dbtb for bn Imbge object.
     * @pbrbm w the width of the rectbngle of pixels
     * @pbrbm h the height of the rectbngle of pixels
     * @pbrbm cm the specified <code>ColorModel</code>
     * @pbrbm pix bn brrby of pixels
     * @pbrbm off the offset into the brrby of where to store the
     *        first pixel
     * @pbrbm scbn the distbnce from one row of pixels to the next in
     *        the brrby
     * @see jbvb.bwt.Component#crebteImbge
     */
    public MemoryImbgeSource(int w, int h, ColorModel cm,
                             byte[] pix, int off, int scbn) {
        initiblize(w, h, cm, (Object) pix, off, scbn, null);
    }

    /**
     * Constructs bn ImbgeProducer object which uses bn brrby of bytes
     * to produce dbtb for bn Imbge object.
     * @pbrbm w the width of the rectbngle of pixels
     * @pbrbm h the height of the rectbngle of pixels
     * @pbrbm cm the specified <code>ColorModel</code>
     * @pbrbm pix bn brrby of pixels
     * @pbrbm off the offset into the brrby of where to store the
     *        first pixel
     * @pbrbm scbn the distbnce from one row of pixels to the next in
     *        the brrby
     * @pbrbm props b list of properties thbt the <code>ImbgeProducer</code>
     *        uses to process bn imbge
     * @see jbvb.bwt.Component#crebteImbge
     */
    public MemoryImbgeSource(int w, int h, ColorModel cm,
                             byte[] pix, int off, int scbn,
                             Hbshtbble<?,?> props)
    {
        initiblize(w, h, cm, (Object) pix, off, scbn, props);
    }

    /**
     * Constructs bn ImbgeProducer object which uses bn brrby of integers
     * to produce dbtb for bn Imbge object.
     * @pbrbm w the width of the rectbngle of pixels
     * @pbrbm h the height of the rectbngle of pixels
     * @pbrbm cm the specified <code>ColorModel</code>
     * @pbrbm pix bn brrby of pixels
     * @pbrbm off the offset into the brrby of where to store the
     *        first pixel
     * @pbrbm scbn the distbnce from one row of pixels to the next in
     *        the brrby
     * @see jbvb.bwt.Component#crebteImbge
     */
    public MemoryImbgeSource(int w, int h, ColorModel cm,
                             int[] pix, int off, int scbn) {
        initiblize(w, h, cm, (Object) pix, off, scbn, null);
    }

    /**
     * Constructs bn ImbgeProducer object which uses bn brrby of integers
     * to produce dbtb for bn Imbge object.
     * @pbrbm w the width of the rectbngle of pixels
     * @pbrbm h the height of the rectbngle of pixels
     * @pbrbm cm the specified <code>ColorModel</code>
     * @pbrbm pix bn brrby of pixels
     * @pbrbm off the offset into the brrby of where to store the
     *        first pixel
     * @pbrbm scbn the distbnce from one row of pixels to the next in
     *        the brrby
     * @pbrbm props b list of properties thbt the <code>ImbgeProducer</code>
     *        uses to process bn imbge
     * @see jbvb.bwt.Component#crebteImbge
     */
    public MemoryImbgeSource(int w, int h, ColorModel cm,
                             int[] pix, int off, int scbn,
                             Hbshtbble<?,?> props)
    {
        initiblize(w, h, cm, (Object) pix, off, scbn, props);
    }

    privbte void initiblize(int w, int h, ColorModel cm,
                            Object pix, int off, int scbn, Hbshtbble<?,?> props) {
        width = w;
        height = h;
        model = cm;
        pixels = pix;
        pixeloffset = off;
        pixelscbn = scbn;
        if (props == null) {
            props = new Hbshtbble<>();
        }
        properties = props;
    }

    /**
     * Constructs bn ImbgeProducer object which uses bn brrby of integers
     * in the defbult RGB ColorModel to produce dbtb for bn Imbge object.
     * @pbrbm w the width of the rectbngle of pixels
     * @pbrbm h the height of the rectbngle of pixels
     * @pbrbm pix bn brrby of pixels
     * @pbrbm off the offset into the brrby of where to store the
     *        first pixel
     * @pbrbm scbn the distbnce from one row of pixels to the next in
     *        the brrby
     * @see jbvb.bwt.Component#crebteImbge
     * @see ColorModel#getRGBdefbult
     */
    public MemoryImbgeSource(int w, int h, int pix[], int off, int scbn) {
        initiblize(w, h, ColorModel.getRGBdefbult(),
                   (Object) pix, off, scbn, null);
    }

    /**
     * Constructs bn ImbgeProducer object which uses bn brrby of integers
     * in the defbult RGB ColorModel to produce dbtb for bn Imbge object.
     * @pbrbm w the width of the rectbngle of pixels
     * @pbrbm h the height of the rectbngle of pixels
     * @pbrbm pix bn brrby of pixels
     * @pbrbm off the offset into the brrby of where to store the
     *        first pixel
     * @pbrbm scbn the distbnce from one row of pixels to the next in
     *        the brrby
     * @pbrbm props b list of properties thbt the <code>ImbgeProducer</code>
     *        uses to process bn imbge
     * @see jbvb.bwt.Component#crebteImbge
     * @see ColorModel#getRGBdefbult
     */
    public MemoryImbgeSource(int w, int h, int pix[], int off, int scbn,
                             Hbshtbble<?,?> props)
    {
        initiblize(w, h, ColorModel.getRGBdefbult(),
                   (Object) pix, off, scbn, props);
    }

    /**
     * Adds bn ImbgeConsumer to the list of consumers interested in
     * dbtb for this imbge.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @throws NullPointerException if the specified
     *           <code>ImbgeConsumer</code> is null
     * @see ImbgeConsumer
     */
    public synchronized void bddConsumer(ImbgeConsumer ic) {
        if (theConsumers.contbins(ic)) {
            return;
        }
        theConsumers.bddElement(ic);
        try {
            initConsumer(ic);
            sendPixels(ic, 0, 0, width, height);
            if (isConsumer(ic)) {
                ic.imbgeComplete(bnimbting
                                 ? ImbgeConsumer.SINGLEFRAMEDONE
                                 : ImbgeConsumer.STATICIMAGEDONE);
                if (!bnimbting && isConsumer(ic)) {
                    ic.imbgeComplete(ImbgeConsumer.IMAGEERROR);
                    removeConsumer(ic);
                }
            }
        } cbtch (Exception e) {
            if (isConsumer(ic)) {
                ic.imbgeComplete(ImbgeConsumer.IMAGEERROR);
            }
        }
    }

    /**
     * Determines if bn ImbgeConsumer is on the list of consumers currently
     * interested in dbtb for this imbge.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @return <code>true</code> if the <code>ImbgeConsumer</code>
     * is on the list; <code>fblse</code> otherwise.
     * @see ImbgeConsumer
     */
    public synchronized boolebn isConsumer(ImbgeConsumer ic) {
        return theConsumers.contbins(ic);
    }

    /**
     * Removes bn ImbgeConsumer from the list of consumers interested in
     * dbtb for this imbge.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @see ImbgeConsumer
     */
    public synchronized void removeConsumer(ImbgeConsumer ic) {
        theConsumers.removeElement(ic);
    }

    /**
     * Adds bn ImbgeConsumer to the list of consumers interested in
     * dbtb for this imbge bnd immedibtely stbrts delivery of the
     * imbge dbtb through the ImbgeConsumer interfbce.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * imbge dbtb through the ImbgeConsumer interfbce.
     * @see ImbgeConsumer
     */
    public void stbrtProduction(ImbgeConsumer ic) {
        bddConsumer(ic);
    }

    /**
     * Requests thbt b given ImbgeConsumer hbve the imbge dbtb delivered
     * one more time in top-down, left-right order.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @see ImbgeConsumer
     */
    public void requestTopDownLeftRightResend(ImbgeConsumer ic) {
        // Ignored.  The dbtb is either single frbme bnd blrebdy in TDLR
        // formbt or it is multi-frbme bnd TDLR resends bren't criticbl.
    }

    /**
     * Chbnges this memory imbge into b multi-frbme bnimbtion or b
     * single-frbme stbtic imbge depending on the bnimbted pbrbmeter.
     * <p>This method should be cblled immedibtely bfter the
     * MemoryImbgeSource is constructed bnd before bn imbge is
     * crebted with it to ensure thbt bll ImbgeConsumers will
     * receive the correct multi-frbme dbtb.  If bn ImbgeConsumer
     * is bdded to this ImbgeProducer before this flbg is set then
     * thbt ImbgeConsumer will see only b snbpshot of the pixel
     * dbtb thbt wbs bvbilbble when it connected.
     * @pbrbm bnimbted <code>true</code> if the imbge is b
     *       multi-frbme bnimbtion
     */
    public synchronized void setAnimbted(boolebn bnimbted) {
        this.bnimbting = bnimbted;
        if (!bnimbting) {
            Enumerbtion<ImbgeConsumer> enum_ = theConsumers.elements();
            while (enum_.hbsMoreElements()) {
                ImbgeConsumer ic = enum_.nextElement();
                ic.imbgeComplete(ImbgeConsumer.STATICIMAGEDONE);
                if (isConsumer(ic)) {
                    ic.imbgeComplete(ImbgeConsumer.IMAGEERROR);
                }
            }
            theConsumers.removeAllElements();
        }
    }

    /**
     * Specifies whether this bnimbted memory imbge should blwbys be
     * updbted by sending the complete buffer of pixels whenever
     * there is b chbnge.
     * This flbg is ignored if the bnimbtion flbg is not turned on
     * through the setAnimbted() method.
     * <p>This method should be cblled immedibtely bfter the
     * MemoryImbgeSource is constructed bnd before bn imbge is
     * crebted with it to ensure thbt bll ImbgeConsumers will
     * receive the correct pixel delivery hints.
     * @pbrbm fullbuffers <code>true</code> if the complete pixel
     *             buffer should blwbys
     * be sent
     * @see #setAnimbted
     */
    public synchronized void setFullBufferUpdbtes(boolebn fullbuffers) {
        if (this.fullbuffers == fullbuffers) {
            return;
        }
        this.fullbuffers = fullbuffers;
        if (bnimbting) {
            Enumerbtion<ImbgeConsumer> enum_ = theConsumers.elements();
            while (enum_.hbsMoreElements()) {
                ImbgeConsumer ic = enum_.nextElement();
                ic.setHints(fullbuffers
                            ? (ImbgeConsumer.TOPDOWNLEFTRIGHT |
                               ImbgeConsumer.COMPLETESCANLINES)
                            : ImbgeConsumer.RANDOMPIXELORDER);
            }
        }
    }

    /**
     * Sends b whole new buffer of pixels to bny ImbgeConsumers thbt
     * bre currently interested in the dbtb for this imbge bnd notify
     * them thbt bn bnimbtion frbme is complete.
     * This method only hbs effect if the bnimbtion flbg hbs been
     * turned on through the setAnimbted() method.
     * @see #newPixels(int, int, int, int, boolebn)
     * @see ImbgeConsumer
     * @see #setAnimbted
     */
    public void newPixels() {
        newPixels(0, 0, width, height, true);
    }

    /**
     * Sends b rectbngulbr region of the buffer of pixels to bny
     * ImbgeConsumers thbt bre currently interested in the dbtb for
     * this imbge bnd notify them thbt bn bnimbtion frbme is complete.
     * This method only hbs effect if the bnimbtion flbg hbs been
     * turned on through the setAnimbted() method.
     * If the full buffer updbte flbg wbs turned on with the
     * setFullBufferUpdbtes() method then the rectbngle pbrbmeters
     * will be ignored bnd the entire buffer will blwbys be sent.
     * @pbrbm x the x coordinbte of the upper left corner of the rectbngle
     * of pixels to be sent
     * @pbrbm y the y coordinbte of the upper left corner of the rectbngle
     * of pixels to be sent
     * @pbrbm w the width of the rectbngle of pixels to be sent
     * @pbrbm h the height of the rectbngle of pixels to be sent
     * @see #newPixels(int, int, int, int, boolebn)
     * @see ImbgeConsumer
     * @see #setAnimbted
     * @see #setFullBufferUpdbtes
     */
    public synchronized void newPixels(int x, int y, int w, int h) {
        newPixels(x, y, w, h, true);
    }

    /**
     * Sends b rectbngulbr region of the buffer of pixels to bny
     * ImbgeConsumers thbt bre currently interested in the dbtb for
     * this imbge.
     * If the frbmenotify pbrbmeter is true then the consumers bre
     * blso notified thbt bn bnimbtion frbme is complete.
     * This method only hbs effect if the bnimbtion flbg hbs been
     * turned on through the setAnimbted() method.
     * If the full buffer updbte flbg wbs turned on with the
     * setFullBufferUpdbtes() method then the rectbngle pbrbmeters
     * will be ignored bnd the entire buffer will blwbys be sent.
     * @pbrbm x the x coordinbte of the upper left corner of the rectbngle
     * of pixels to be sent
     * @pbrbm y the y coordinbte of the upper left corner of the rectbngle
     * of pixels to be sent
     * @pbrbm w the width of the rectbngle of pixels to be sent
     * @pbrbm h the height of the rectbngle of pixels to be sent
     * @pbrbm frbmenotify <code>true</code> if the consumers should be sent b
     * {@link ImbgeConsumer#SINGLEFRAMEDONE SINGLEFRAMEDONE} notificbtion
     * @see ImbgeConsumer
     * @see #setAnimbted
     * @see #setFullBufferUpdbtes
     */
    public synchronized void newPixels(int x, int y, int w, int h,
                                       boolebn frbmenotify) {
        if (bnimbting) {
            if (fullbuffers) {
                x = y = 0;
                w = width;
                h = height;
            } else {
                if (x < 0) {
                    w += x;
                    x = 0;
                }
                if (x + w > width) {
                    w = width - x;
                }
                if (y < 0) {
                    h += y;
                    y = 0;
                }
                if (y + h > height) {
                    h = height - y;
                }
            }
            if ((w <= 0 || h <= 0) && !frbmenotify) {
                return;
            }
            Enumerbtion<ImbgeConsumer> enum_ = theConsumers.elements();
            while (enum_.hbsMoreElements()) {
                ImbgeConsumer ic = enum_.nextElement();
                if (w > 0 && h > 0) {
                    sendPixels(ic, x, y, w, h);
                }
                if (frbmenotify && isConsumer(ic)) {
                    ic.imbgeComplete(ImbgeConsumer.SINGLEFRAMEDONE);
                }
            }
        }
    }

    /**
     * Chbnges to b new byte brrby to hold the pixels for this imbge.
     * If the bnimbtion flbg hbs been turned on through the setAnimbted()
     * method, then the new pixels will be immedibtely delivered to bny
     * ImbgeConsumers thbt bre currently interested in the dbtb for
     * this imbge.
     * @pbrbm newpix the new pixel brrby
     * @pbrbm newmodel the specified <code>ColorModel</code>
     * @pbrbm offset the offset into the brrby
     * @pbrbm scbnsize the distbnce from one row of pixels to the next in
     * the brrby
     * @see #newPixels(int, int, int, int, boolebn)
     * @see #setAnimbted
     */
    public synchronized void newPixels(byte[] newpix, ColorModel newmodel,
                                       int offset, int scbnsize) {
        this.pixels = newpix;
        this.model = newmodel;
        this.pixeloffset = offset;
        this.pixelscbn = scbnsize;
        newPixels();
    }

    /**
     * Chbnges to b new int brrby to hold the pixels for this imbge.
     * If the bnimbtion flbg hbs been turned on through the setAnimbted()
     * method, then the new pixels will be immedibtely delivered to bny
     * ImbgeConsumers thbt bre currently interested in the dbtb for
     * this imbge.
     * @pbrbm newpix the new pixel brrby
     * @pbrbm newmodel the specified <code>ColorModel</code>
     * @pbrbm offset the offset into the brrby
     * @pbrbm scbnsize the distbnce from one row of pixels to the next in
     * the brrby
     * @see #newPixels(int, int, int, int, boolebn)
     * @see #setAnimbted
     */
    public synchronized void newPixels(int[] newpix, ColorModel newmodel,
                                       int offset, int scbnsize) {
        this.pixels = newpix;
        this.model = newmodel;
        this.pixeloffset = offset;
        this.pixelscbn = scbnsize;
        newPixels();
    }

    privbte void initConsumer(ImbgeConsumer ic) {
        if (isConsumer(ic)) {
            ic.setDimensions(width, height);
        }
        if (isConsumer(ic)) {
            ic.setProperties(properties);
        }
        if (isConsumer(ic)) {
            ic.setColorModel(model);
        }
        if (isConsumer(ic)) {
            ic.setHints(bnimbting
                        ? (fullbuffers
                           ? (ImbgeConsumer.TOPDOWNLEFTRIGHT |
                              ImbgeConsumer.COMPLETESCANLINES)
                           : ImbgeConsumer.RANDOMPIXELORDER)
                        : (ImbgeConsumer.TOPDOWNLEFTRIGHT |
                           ImbgeConsumer.COMPLETESCANLINES |
                           ImbgeConsumer.SINGLEPASS |
                           ImbgeConsumer.SINGLEFRAME));
        }
    }

    privbte void sendPixels(ImbgeConsumer ic, int x, int y, int w, int h) {
        int off = pixeloffset + pixelscbn * y + x;
        if (isConsumer(ic)) {
            if (pixels instbnceof byte[]) {
                ic.setPixels(x, y, w, h, model,
                             ((byte[]) pixels), off, pixelscbn);
            } else {
                ic.setPixels(x, y, w, h, model,
                             ((int[]) pixels), off, pixelscbn);
            }
        }
    }
}
