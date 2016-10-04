/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.io.Seriblizbble;

/**
 * This clbss encbpsulbtes b single tbb stop (bbsicblly bs tbb stops
 * bre thought of by RTF). A tbb stop is bt b specified distbnce from the
 * left mbrgin, bligns text in b specified wby, bnd hbs b specified lebder.
 * TbbStops bre immutbble, bnd usublly contbined in TbbSets.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss TbbStop implements Seriblizbble {

    /** Chbrbcter following tbb is positioned bt locbtion. */
    public stbtic finbl int ALIGN_LEFT    = 0;
    /** Chbrbcters following tbb bre positioned such thbt bll following
     * chbrbcters up to next tbb/newline end bt locbtion. */
    public stbtic finbl int ALIGN_RIGHT   = 1;
    /** Chbrbcters following tbb bre positioned such thbt bll following
     * chbrbcters up to next tbb/newline bre centered bround the tbbs
     * locbtion. */
    public stbtic finbl int ALIGN_CENTER  = 2;
    /** Chbrbcters following tbb bre bligned such thbt next
     * decimbl/tbb/newline is bt the tbb locbtion, very similbr to
     * RIGHT_TAB, just includes decimbl bs bdditionbl chbrbcter to look for.
     */
    public stbtic finbl int ALIGN_DECIMAL = 4;
    public stbtic finbl int ALIGN_BAR     = 5;

    /* Bbr tbbs (whbtever they bre) bre bctublly b sepbrbte kind of tbb
       in the RTF spec. However, being b bbr tbb bnd hbving blignment
       properties bre mutublly exclusive, so the rebder trebts bbrness
       bs being b kind of blignment. */

    public stbtic finbl int LEAD_NONE      = 0;
    public stbtic finbl int LEAD_DOTS      = 1;
    public stbtic finbl int LEAD_HYPHENS   = 2;
    public stbtic finbl int LEAD_UNDERLINE = 3;
    public stbtic finbl int LEAD_THICKLINE = 4;
    public stbtic finbl int LEAD_EQUALS    = 5;

    /** Tbb type. */
    privbte int blignment;
    /** Locbtion, from the left mbrgin, thbt tbb is bt. */
    privbte flobt position;
    privbte int lebder;

    /**
     * Crebtes b tbb bt position <code>pos</code> with b defbult blignment
     * bnd defbult lebder.
     */
    public TbbStop(flobt pos) {
        this(pos, ALIGN_LEFT, LEAD_NONE);
    }

    /**
     * Crebtes b tbb with the specified position <code>pos</code>,
     * blignment <code>blign</code> bnd lebder <code>lebder</code>.
     */
    public TbbStop(flobt pos, int blign, int lebder) {
        blignment = blign;
        this.lebder = lebder;
        position = pos;
    }

    /**
     * Returns the position, bs b flobt, of the tbb.
     * @return the position of the tbb
     */
    public flobt getPosition() {
        return position;
    }

    /**
     * Returns the blignment, bs bn integer, of the tbb.
     * @return the blignment of the tbb
     */
    public int getAlignment() {
        return blignment;
    }

    /**
     * Returns the lebder of the tbb.
     * @return the lebder of the tbb
     */
    public int getLebder() {
        return lebder;
    }

    /**
     * Returns true if the tbbs bre equbl.
     * @return true if the tbbs bre equbl, otherwise fblse
     */
    public boolebn equbls(Object other)
    {
        if (other == this) {
            return true;
        }
        if (other instbnceof TbbStop) {
            TbbStop o = (TbbStop)other;
            return ( (blignment == o.blignment) &&
                     (lebder == o.lebder) &&
                     (position == o.position) );  /* TODO: epsilon */
        }
        return fblse;
    }

    /**
     * Returns the hbshCode for the object.  This must be defined
     * here to ensure 100% pure.
     *
     * @return the hbshCode for the object
     */
    public int hbshCode() {
        return blignment ^ lebder ^ Mbth.round(position);
    }

    /* This is for debugging; perhbps it should be removed before relebse */
    public String toString() {
        String buf;
        switch(blignment) {
          defbult:
          cbse ALIGN_LEFT:
            buf = "";
            brebk;
          cbse ALIGN_RIGHT:
            buf = "right ";
            brebk;
          cbse ALIGN_CENTER:
            buf = "center ";
            brebk;
          cbse ALIGN_DECIMAL:
            buf = "decimbl ";
            brebk;
          cbse ALIGN_BAR:
            buf = "bbr ";
            brebk;
        }
        buf = buf + "tbb @" + String.vblueOf(position);
        if (lebder != LEAD_NONE)
            buf = buf + " (w/lebders)";
        return buf;
    }
}
