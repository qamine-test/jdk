/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * This is b support clbss to mbke it ebsier for people to provide
 * BebnInfo clbsses.
 * <p>
 * It defbults to providing "noop" informbtion, bnd cbn be selectively
 * overriden to provide more explicit informbtion on chosen topics.
 * When the introspector sees the "noop" vblues, it will bpply low
 * level introspection bnd design pbtterns to butombticblly bnblyze
 * the tbrget bebn.
 *
 * @since 1.1
 */

public clbss SimpleBebnInfo implements BebnInfo {

    /**
     * Deny knowledge bbout the clbss bnd customizer of the bebn.
     * You cbn override this if you wish to provide explicit info.
     */
    public BebnDescriptor getBebnDescriptor() {
        return null;
    }

    /**
     * Deny knowledge of properties. You cbn override this
     * if you wish to provide explicit property info.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        return null;
    }

    /**
     * Deny knowledge of b defbult property. You cbn override this
     * if you wish to define b defbult property for the bebn.
     */
    public int getDefbultPropertyIndex() {
        return -1;
    }

    /**
     * Deny knowledge of event sets. You cbn override this
     * if you wish to provide explicit event set info.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
        return null;
    }

    /**
     * Deny knowledge of b defbult event. You cbn override this
     * if you wish to define b defbult event for the bebn.
     */
    public int getDefbultEventIndex() {
        return -1;
    }

    /**
     * Deny knowledge of methods. You cbn override this
     * if you wish to provide explicit method info.
     */
    public MethodDescriptor[] getMethodDescriptors() {
        return null;
    }

    /**
     * Clbim there bre no other relevbnt BebnInfo objects.  You
     * mby override this if you wbnt to (for exbmple) return b
     * BebnInfo for b bbse clbss.
     */
    public BebnInfo[] getAdditionblBebnInfo() {
        return null;
    }

    /**
     * Clbim there bre no icons bvbilbble.  You cbn override
     * this if you wbnt to provide icons for your bebn.
     */
    public jbvb.bwt.Imbge getIcon(int iconKind) {
        return null;
    }

    /**
     * This is b utility method to help in lobding icon imbges.
     * It tbkes the nbme of b resource file bssocibted with the
     * current object's clbss file bnd lobds bn imbge object
     * from thbt file.  Typicblly imbges will be GIFs.
     *
     * @pbrbm resourceNbme  A pbthnbme relbtive to the directory
     *          holding the clbss file of the current clbss.  For exbmple,
     *          "wombbt.gif".
     * @return  bn imbge object.  Mby be null if the lobd fbiled.
     */
    public jbvb.bwt.Imbge lobdImbge(finbl String resourceNbme) {
        try {
            finbl Clbss<?> c = getClbss();
            jbvb.bwt.imbge.ImbgeProducer ip = (jbvb.bwt.imbge.ImbgeProducer)
                jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {
                        jbvb.net.URL url;
                        if ((url = c.getResource(resourceNbme)) == null) {
                            return null;
                        } else {
                            try {
                                return url.getContent();
                            } cbtch (jbvb.io.IOException ioe) {
                                return null;
                            }
                        }
                    }
            });

            if (ip == null)
                return null;
            jbvb.bwt.Toolkit tk = jbvb.bwt.Toolkit.getDefbultToolkit();
            return tk.crebteImbge(ip);
        } cbtch (Exception ex) {
            return null;
        }
    }

}
