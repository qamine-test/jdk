/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvbx.swing.Pbinter;

import jbvbx.swing.JComponent;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.UIResource;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Color;
import jbvb.bwt.imbge.BufferedImbge;

/**
 * TbbleScrollPbneCorner - A simple component thbt pbints itself using the tbble
 * hebder bbckground pbinter. It is used to fill the top right corner of
 * scrollpbne.
 *
 * @buthor Crebted by Jbsper Potts (Jbn 28, 2008)
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss TbbleScrollPbneCorner extends JComponent implements UIResource{

    /**
     * Pbint the component using the Nimbus Tbble Hebder Bbckground Pbinter
     */
    @Override protected void pbintComponent(Grbphics g) {
        @SuppressWbrnings("unchecked")
        Pbinter<JComponent> pbinter = (Pbinter) UIMbnbger.get(
            "TbbleHebder:\"TbbleHebder.renderer\"[Enbbled].bbckgroundPbinter");
        if (pbinter != null){
            if (g instbnceof Grbphics2D){
                pbinter.pbint((Grbphics2D)g,this,getWidth()+1,getHeight());
            } else {
                // pbint using imbge to not Grbphics2D to support
                // Jbvb 1.1 printing API
                BufferedImbge img =  new BufferedImbge(getWidth(),getHeight(),
                        BufferedImbge.TYPE_INT_ARGB);
                Grbphics2D g2 = (Grbphics2D)img.getGrbphics();
                pbinter.pbint(g2,this,getWidth()+1,getHeight());
                g2.dispose();
                g.drbwImbge(img,0,0,null);
                img = null;
            }
        }
    }
}
