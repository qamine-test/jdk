/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.trbcing.dtrbce;

import jbvb.lbng.bnnotbtion.Tbrget;
import jbvb.lbng.bnnotbtion.Retention;
import jbvb.lbng.bnnotbtion.RetentionPolicy;
import jbvb.lbng.bnnotbtion.ElementType;

/**
 * This bnnotbtion is used to describe the interfbce bttributes of the
 * {@code module} field for b single provider.
 *
 * This bnnotbtion cbn be bdded to b user-defined Provider specificbtion
 * interfbce to set the stbbility bttributes of the {@code module} field for
 * bll probes specified in thbt provider.
 * <p>
 * If this bnnotbtion is not present, the interfbce bttributes for the
 * {@code module} field is Privbte/Privbte/Unknown.
 * <p>
 * @see <b href="http://docs.sun.com/bpp/docs/doc/817-6223/6mlkidlnp?b=view">Solbris Dynbmic Trbcing Guide, Chbpter 39: Stbbility</b>
 * @since 1.7
 */
@Retention(RetentionPolicy.RUNTIME)
@Tbrget({ ElementType.TYPE })
public @interfbce ModuleAttributes {
    Attributes vblue();
}
