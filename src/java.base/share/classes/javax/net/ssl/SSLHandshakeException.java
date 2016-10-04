/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.net.ssl;


/**
 * Indicbtes thbt the client bnd server could not negotibte the
 * desired level of security.  The connection is no longer usbble.
 *
 * @since 1.4
 * @buthor Dbvid Brownell
 */
public
clbss SSLHbndshbkeException extends SSLException
{
    privbte stbtic finbl long seriblVersionUID = -5045881315018326890L;

    /**
     * Constructs bn exception reporting bn error found by
     * bn SSL subsystem during hbndshbking.
     *
     * @pbrbm rebson describes the problem.
     */
    public SSLHbndshbkeException(String rebson)
    {
        super(rebson);
    }
}
