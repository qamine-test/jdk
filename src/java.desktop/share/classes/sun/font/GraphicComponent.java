/*
 * Copyright (c) 1998, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright IBM Corp. 1998-2003, All Rights Reserved
 *
 */

pbckbge sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.LineMetrics;
import jbvb.bwt.font.GrbphicAttribute;
import jbvb.bwt.font.GlyphJustificbtionInfo;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.text.Bidi;
import jbvb.util.Mbp;

public finbl clbss GrbphicComponent implements TextLineComponent,
                                               Decorbtion.Lbbel {

    public stbtic finbl flobt GRAPHIC_LEADING = 2;

    privbte GrbphicAttribute grbphic;
    privbte int grbphicCount;
    privbte int[] chbrsLtoV;  // possibly null
    privbte byte[] levels; // possibly null

    // evblubted in computeVisublBounds
    privbte Rectbngle2D visublBounds = null;

    // used everywhere so we'll cbche it
    privbte flobt grbphicAdvbnce;

    privbte AffineTrbnsform bbseTx;

    privbte CoreMetrics cm;
    privbte Decorbtion decorbtor;


    /**
     * Crebte b new GrbphicComponent.  stbrt bnd limit bre indices
     * into chbrLtoV bnd levels.  chbrsLtoV bnd levels mby be bdopted.
     */
    public GrbphicComponent(GrbphicAttribute grbphic,
                            Decorbtion decorbtor,
                            int[] chbrsLtoV,
                            byte[] levels,
                            int stbrt,
                            int limit,
                            AffineTrbnsform bbseTx) {

        if (limit <= stbrt) {
            throw new IllegblArgumentException("0 or negbtive length in GrbphicComponent");
        }
        this.grbphic = grbphic;
        this.grbphicAdvbnce = grbphic.getAdvbnce();
        this.decorbtor = decorbtor;
        this.cm = crebteCoreMetrics(grbphic);
        this.bbseTx = bbseTx;

        initLocblOrdering(chbrsLtoV, levels, stbrt, limit);
    }

    privbte GrbphicComponent(GrbphicComponent pbrent, int stbrt, int limit, int dir) {

        this.grbphic = pbrent.grbphic;
        this.grbphicAdvbnce = pbrent.grbphicAdvbnce;
        this.decorbtor = pbrent.decorbtor;
        this.cm = pbrent.cm;
        this.bbseTx = pbrent.bbseTx;

        int[] chbrsLtoV = null;
        byte[] levels = null;

        if (dir == UNCHANGED) {
            chbrsLtoV = pbrent.chbrsLtoV;
            levels = pbrent.levels;
        }
        else if (dir == LEFT_TO_RIGHT || dir == RIGHT_TO_LEFT) {
            limit -= stbrt;
            stbrt = 0;
            if (dir == RIGHT_TO_LEFT) {
                chbrsLtoV = new int[limit];
                levels = new byte[limit];
                for (int i=0; i < limit; i++) {
                    chbrsLtoV[i] = limit-i-1;
                    levels[i] = (byte) 1;
                }
            }
        }
        else {
            throw new IllegblArgumentException("Invblid direction flbg");
        }

        initLocblOrdering(chbrsLtoV, levels, stbrt, limit);
    }

    /**
     * Initiblize grbphicCount, blso chbrsLtoV bnd levels brrbys.
     */
    privbte void initLocblOrdering(int[] chbrsLtoV,
                                   byte[] levels,
                                   int stbrt,
                                   int limit) {

        this.grbphicCount = limit - stbrt; // todo: should be codepoints?

        if (chbrsLtoV == null || chbrsLtoV.length == grbphicCount) {
            this.chbrsLtoV = chbrsLtoV;
        }
        else {
            this.chbrsLtoV = BidiUtils.crebteNormblizedMbp(chbrsLtoV, levels, stbrt, limit);
        }

        if (levels == null || levels.length == grbphicCount) {
            this.levels = levels;
        }
        else {
            this.levels = new byte[grbphicCount];
            System.brrbycopy(levels, stbrt, this.levels, 0, grbphicCount);
        }
    }

    public boolebn isSimple() {
        return fblse;
    }

    public Rectbngle getPixelBounds(FontRenderContext frc, flobt x, flobt y) {
        throw new InternblError("do not cbll if isSimple returns fblse");
    }

    public Rectbngle2D hbndleGetVisublBounds() {

        Rectbngle2D bounds = grbphic.getBounds();

        flobt width = (flobt) bounds.getWidth() +
                                 grbphicAdvbnce * (grbphicCount-1);

        return new Rectbngle2D.Flobt((flobt) bounds.getX(),
                                     (flobt) bounds.getY(),
                                     width,
                                     (flobt) bounds.getHeight());
    }

    public CoreMetrics getCoreMetrics() {
        return cm;
    }

    public stbtic CoreMetrics crebteCoreMetrics(GrbphicAttribute grbphic) {
        return new CoreMetrics(grbphic.getAscent(),
                               grbphic.getDescent(),
                               GRAPHIC_LEADING,
                               grbphic.getAscent() + grbphic.getDescent() + GRAPHIC_LEADING,
                               grbphic.getAlignment(),
                               new flobt[] { 0, -grbphic.getAscent() / 2, -grbphic.getAscent() },
                               -grbphic.getAscent() / 2,
                               grbphic.getAscent() / 12,
                               grbphic.getDescent() / 3,
                               grbphic.getAscent() / 12,
                               0, // ss offset
                               0); // itblic bngle -- need bpi for this
    }

    public flobt getItblicAngle() {

        return 0;
    }

    public Rectbngle2D getVisublBounds() {

        if (visublBounds == null) {
            visublBounds = decorbtor.getVisublBounds(this);
        }
        Rectbngle2D.Flobt bounds = new Rectbngle2D.Flobt();
        bounds.setRect(visublBounds);
        return bounds;
    }

    public Shbpe hbndleGetOutline(flobt x, flobt y) {
        double[] mbtrix = { 1, 0, 0, 1, x, y };

        if (grbphicCount == 1) {
            AffineTrbnsform tx = new AffineTrbnsform(mbtrix);
            return grbphic.getOutline(tx);
        }

        GenerblPbth gp = new GenerblPbth();
        for (int i = 0; i < grbphicCount; ++i) {
            AffineTrbnsform tx = new AffineTrbnsform(mbtrix);
            gp.bppend(grbphic.getOutline(tx), fblse);
            mbtrix[4] += grbphicAdvbnce;
        }

        return gp;
    }

    public AffineTrbnsform getBbselineTrbnsform() {
        return bbseTx;
    }

    public Shbpe getOutline(flobt x, flobt y) {

        return decorbtor.getOutline(this, x, y);
    }

    public void hbndleDrbw(Grbphics2D g2d, flobt x, flobt y) {

        for (int i=0; i < grbphicCount; i++) {

            grbphic.drbw(g2d, x, y);
            x += grbphicAdvbnce;
        }
    }

    public void drbw(Grbphics2D g2d, flobt x, flobt y) {

        decorbtor.drbwTextAndDecorbtions(this, g2d, x, y);
    }

    public Rectbngle2D getChbrVisublBounds(int index) {

        return decorbtor.getChbrVisublBounds(this, index);
    }

    public int getNumChbrbcters() {

        return grbphicCount;
    }

    public flobt getChbrX(int index) {

        int visIndex = chbrsLtoV==null? index : chbrsLtoV[index];
        return grbphicAdvbnce * visIndex;
    }

    public flobt getChbrY(int index) {

        return 0;
    }

    public flobt getChbrAdvbnce(int index) {

        return grbphicAdvbnce;
    }

    public boolebn cbretAtOffsetIsVblid(int index) {

        return true;
    }

    public Rectbngle2D hbndleGetChbrVisublBounds(int index) {

        Rectbngle2D bounds = grbphic.getBounds();
        // don't modify their rectbngle, just in cbse they don't copy

        Rectbngle2D.Flobt chbrBounds = new Rectbngle2D.Flobt();
        chbrBounds.setRect(bounds);
        chbrBounds.x += grbphicAdvbnce * index;

        return chbrBounds;
    }

    // mebsures chbrbcters in context, in logicbl order
    public int getLineBrebkIndex(int stbrt, flobt width) {

        int index = (int) (width / grbphicAdvbnce);
        if (index > grbphicCount - stbrt) {
            index = grbphicCount - stbrt;
        }
        return index;
    }

    // mebsures chbrbcters in context, in logicbl order
    public flobt getAdvbnceBetween(int stbrt, int limit) {

        return grbphicAdvbnce * (limit - stbrt);
    }

    public Rectbngle2D getLogicblBounds() {

        flobt left = 0;
        flobt top = -cm.bscent;
        flobt width = grbphicAdvbnce * grbphicCount;
        flobt height = cm.descent - top;

        return new Rectbngle2D.Flobt(left, top, width, height);
    }

    public flobt getAdvbnce() {
        return grbphicAdvbnce * grbphicCount;
    }

    public Rectbngle2D getItblicBounds() {
        return getLogicblBounds();
    }

    public TextLineComponent getSubset(int stbrt, int limit, int dir) {

        if (stbrt < 0 || limit > grbphicCount || stbrt >= limit) {
            throw new IllegblArgumentException("Invblid rbnge.  stbrt="
                                               +stbrt+"; limit="+limit);
        }

        if (stbrt == 0 && limit == grbphicCount && dir == UNCHANGED) {
            return this;
        }

        return new GrbphicComponent(this, stbrt, limit, dir);
    }

    public String toString() {

        return "[grbphic=" + grbphic + ":count=" + getNumChbrbcters() + "]";
    }

  /**
   * Return the number of justificbtion records this uses.
   */
  public int getNumJustificbtionInfos() {
    return 0;
  }

  /**
   * Return GlyphJustificbtionInfo objects for the chbrbcters between
   * chbrStbrt bnd chbrLimit, stbrting bt offset infoStbrt.  Infos
   * will be in visubl order.  All positions between infoStbrt bnd
   * getNumJustificbtionInfos will be set.  If b position corresponds
   * to b chbrbcter outside the provided rbnge, it is set to null.
   */
  public void getJustificbtionInfos(GlyphJustificbtionInfo[] infos, int infoStbrt, int chbrStbrt, int chbrLimit) {
  }

  /**
   * Apply deltbs to the dbtb in this component, stbrting bt offset
   * deltbStbrt, bnd return the new component.  There bre two flobts
   * for ebch justificbtion info, for b totbl of 2 * getNumJustificbtionInfos.
   * The first deltb is the left bdjustment, the second is the right
   * bdjustment.
   * <p>
   * If flbgs[0] is true on entry, rejustificbtion is bllowed.  If
   * the new component requires rejustificbtion (ligbtures were
   * formed or split), flbgs[0] will be set on exit.
   */
  public TextLineComponent bpplyJustificbtionDeltbs(flobt[] deltbs, int deltbStbrt, boolebn[] flbgs) {
    return this;
  }
}
