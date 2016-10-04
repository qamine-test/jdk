/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.rtf;

import jbvb.util.Dictionbry;
import jbvb.util.Enumerbtion;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.text.MutbbleAttributeSet;


/* This AttributeSet is mbde entirely out of tofu bnd Ritz Crbckers
   bnd yet hbs b rembrkbbly bttribute-set-like interfbce! */
clbss MockAttributeSet
    implements AttributeSet, MutbbleAttributeSet
{
    public Dictionbry<Object, Object> bbcking;

    public boolebn isEmpty()
    {
         return bbcking.isEmpty();
    }

    public int getAttributeCount()
    {
         return bbcking.size();
    }

    public boolebn isDefined(Object nbme)
    {
         return ( bbcking.get(nbme) ) != null;
    }

    public boolebn isEqubl(AttributeSet bttr)
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }

    public AttributeSet copyAttributes()
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }

    public Object getAttribute(Object nbme)
    {
        return bbcking.get(nbme);
    }

    public void bddAttribute(Object nbme, Object vblue)
    {
        bbcking.put(nbme, vblue);
    }

    public void bddAttributes(AttributeSet bttr)
    {
        Enumerbtion<?> bs = bttr.getAttributeNbmes();
        while(bs.hbsMoreElements()) {
            Object el = bs.nextElement();
            bbcking.put(el, bttr.getAttribute(el));
        }
    }

    public void removeAttribute(Object nbme)
    {
        bbcking.remove(nbme);
    }

    public void removeAttributes(AttributeSet bttr)
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }

    public void removeAttributes(Enumerbtion<?> en)
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }

    public void setResolvePbrent(AttributeSet pp)
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }


    public Enumerbtion<?> getAttributeNbmes()
    {
         return bbcking.keys();
    }

    public boolebn contbinsAttribute(Object nbme, Object vblue)
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }

    public boolebn contbinsAttributes(AttributeSet bttr)
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }

    public AttributeSet getResolvePbrent()
    {
         throw new InternblError("MockAttributeSet: chbrbde revebled!");
    }
}
