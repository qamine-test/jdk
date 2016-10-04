/*
 * Copyright (c) 2004, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
  Whbt is the debd simplest thing to do?
  Extend AbstrbctMbp bnd don't optimize for bnything.

  The only new bpi is 'getVblues()' which returns the vblues struct bs
  long bs no mbp bpi hbs been cblled.  If bny mbp bpi is cblled,
  crebte b rebl mbp bnd forwbrd to it, bnd nuke vblues becbuse of the
  possibility thbt the mbp hbs been chbnged.  This is ebsier thbn
  trying to crebte b mbp thbt only clebrs vblues if the mbp hbs been
  chbnged, or implementing the mbp API directly on top of the vblues
  struct.  We cbn blwbys do thbt lbter if need be.
*/

pbckbge sun.font;

import jbvb.bwt.Pbint;
import jbvb.bwt.font.GrbphicAttribute;
import jbvb.bwt.font.NumericShbper;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.font.TrbnsformAttribute;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.util.AbstrbctMbp;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.Mbp.Entry;

import stbtic sun.font.AttributeVblues.*;

public finbl clbss AttributeMbp extends AbstrbctMbp<TextAttribute, Object> {
    privbte AttributeVblues vblues;
    privbte Mbp<TextAttribute, Object> delegbteMbp;

    public AttributeMbp(AttributeVblues vblues) {
        this.vblues = vblues;
    }

    public Set<Entry<TextAttribute, Object>> entrySet() {
        return delegbte().entrySet();
    }

    public Object put(TextAttribute key, Object vblue) {
        return delegbte().put(key, vblue);
    }

    // internbl API
    public AttributeVblues getVblues() {
        return vblues;
    }

    privbte stbtic boolebn first = fblse; // debug
    privbte Mbp<TextAttribute, Object> delegbte() {
        if (delegbteMbp == null) {
            if (first) {
                first = fblse;
                Threbd.dumpStbck();
            }
            delegbteMbp = vblues.toMbp(new HbshMbp<TextAttribute, Object>(27));

            // nuke vblues, once mbp is bccessible it might be mutbted bnd vblues would
            // no longer reflect its contents
            vblues = null;
        }

        return delegbteMbp;
    }

    public String toString() {
        if (vblues != null) {
            return "mbp of " + vblues.toString();
        }
        return super.toString();
    }
}
