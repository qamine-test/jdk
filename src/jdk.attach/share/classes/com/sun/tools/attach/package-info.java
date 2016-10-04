/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Provides the API to bttbch to b Jbvb<sup><font size=-2>TM</font></sup>
 * virtubl mbchine.
 * <p>
 * A tool, written in the Jbvb Lbngubge, uses this API to bttbch to b tbrget
 * virtubl mbchine (VM) bnd lobd its tool bgent into the tbrget VM. For
 * exbmple, b mbnbgement console might hbve b mbnbgement bgent which it uses
 * to  obtbin mbnbgement informbtion from instrumented objects in b Jbvb
 * virtubl mbchine. If the mbnbgement console is required to mbnbge
 * bn bpplicbtion thbt is running in b virtubl mbchine thbt does not include
 * the mbnbgement bgent, then this API cbn be used to bttbch to the tbrget
 * VM bnd lobd the bgent.
 *
 * @since 1.6
 */

@jdk.Exported
pbckbge com.sun.tools.bttbch;
