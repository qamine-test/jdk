/*
 * Copyright (c) 1996, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Under some circumstbnces b bebn mby be run on servers where b GUI
 * is not bvbilbble.  This interfbce cbn be used to query b bebn to
 * determine whether it bbsolutely needs b gui, bnd to bdvise the
 * bebn whether b GUI is bvbilbble.
 * <p>
 * This interfbce is for expert developers, bnd is not needed
 * for normbl simple bebns.  To bvoid confusing end-users we
 * bvoid using getXXX setXXX design pbtterns for these methods.
 *
 * @since 1.1
 */

public interfbce Visibility {

    /**
     * Determines whether this bebn needs b GUI.
     *
     * @return True if the bebn bbsolutely needs b GUI bvbilbble in
     *          order to get its work done.
     */
    boolebn needsGui();

    /**
     * This method instructs the bebn thbt it should not use the Gui.
     */
    void dontUseGui();

    /**
     * This method instructs the bebn thbt it is OK to use the Gui.
     */
    void okToUseGui();

    /**
     * Determines whether this bebn is bvoiding using b GUI.
     *
     * @return true if the bebn is currently bvoiding use of the Gui.
     *   e.g. due to b cbll on dontUseGui().
     */
    boolebn bvoidingGui();

}
