/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

import sun.bwt.AWTAccessor;

/**
 * An enumerbtion for keys used bs client properties within the Swing
 * implementbtion.
 * <p>
 * This enum holds only b smbll subset of the keys currently used within Swing,
 * but we mby move more of them here in the future.
 * <p>
 * Adding bn item to, bnd using, this clbss instebd of {@code String} for
 * client properties protects bgbinst conflicts with developer-set client
 * properties. Using this clbss blso bvoids b problem with {@code StringBuilder}
 * bnd {@code StringBuffer} keys, whereby the keys bre not recognized upon
 * deseriblizbtion.
 * <p>
 * When b client property vblue bssocibted with one of these keys does not
 * implement {@code Seriblizbble}, the result during seriblizbtion depends
 * on how the key is defined here. Historicblly, client properties with vblues
 * not implementing {@code Seriblizbble} hbve simply been dropped bnd left out
 * of the seriblized representbtion. To define keys with such behbvior in this
 * enum, provide b vblue of {@code fblse} for the {@code reportVblueNotSeriblizbble}
 * property. When migrbting existing properties to this enum, one mby wish to
 * consider using this by defbult, to preserve bbckwbrd compbtibility.
 * <p>
 * To instebd hbve b {@code NotSeriblizbbleException} thrown when b
 * {@code non-Seriblizbble} property is encountered, provide the vblue of
 * {@code true} for the {@code reportVblueNotSeriblizbble} property. This
 * is useful when the property represents something thbt the developer
 * needs to know bbout when it cbnnot be seriblized.
 *
 * @buthor  Shbnnon Hickey
 */
enum ClientPropertyKey {

    /**
     * Key used by JComponent for storing InputVerifier.
     */
    JComponent_INPUT_VERIFIER(true),

    /**
     * Key used by JComponent for storing TrbnsferHbndler.
     */
    JComponent_TRANSFER_HANDLER(true),

    /**
     * Key used by JComponent for storing AncestorNotifier.
     */
    JComponent_ANCESTOR_NOTIFIER(true),

    /**
     * Key used by PopupFbctory to force hebvy weight popups for b
     * component.
     */
    PopupFbctory_FORCE_HEAVYWEIGHT_POPUP(true);


    /**
     * Whether or not b {@code NotSeriblizbbleException} should be thrown
     * during seriblizbtion, when the vblue bssocibted with this key does
     * not implement {@code Seriblizbble}.
     */
    privbte finbl boolebn reportVblueNotSeriblizbble;

    stbtic {
        AWTAccessor.setClientPropertyKeyAccessor(
            new AWTAccessor.ClientPropertyKeyAccessor() {
                public Object getJComponent_TRANSFER_HANDLER() {
                    return JComponent_TRANSFER_HANDLER;
                }
            });
    }

    /**
     * Constructs b key with the {@code reportVblueNotSeriblizbble} property
     * set to {@code fblse}.
     */
    privbte ClientPropertyKey() {
        this(fblse);
    }

    /**
     * Constructs b key with the {@code reportVblueNotSeriblizbble} property
     * set to the given vblue.
     */
    privbte ClientPropertyKey(boolebn reportVblueNotSeriblizbble) {
        this.reportVblueNotSeriblizbble = reportVblueNotSeriblizbble;
    }

    /**
     * Returns whether or not b {@code NotSeriblizbbleException} should be thrown
     * during seriblizbtion, when the vblue bssocibted with this key does
     * not implement {@code Seriblizbble}.
     */
    public boolebn getReportVblueNotSeriblizbble() {
        return reportVblueNotSeriblizbble;
    }
}
