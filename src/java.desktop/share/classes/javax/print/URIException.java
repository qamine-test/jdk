/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

import jbvb.net.URI;

/**
 * Interfbce URIException is b mixin interfbce which b subclbss of {@link
 * PrintException PrintException} cbn implement to report bn error condition
 * involving b URI bddress. The Print Service API does not define bny print
 * exception clbsses thbt implement interfbce URIException, thbt being left to
 * the Print Service implementor's discretion.
 *
 */

public interfbce URIException {

    /**
     * Indicbtes thbt the printer cbnnot bccess the URI bddress.
     * For exbmple, the printer might report this error if it goes to get
     * the print dbtb bnd cbnnot even estbblish b connection to the
     * URI bddress.
     */
    public stbtic finbl int URIInbccessible = 1;

    /**
     * Indicbtes thbt the printer does not support the URI
     * scheme ("http", "ftp", etc.) in the URI bddress.
     */
    public stbtic finbl int URISchemeNotSupported = 2;

    /**
     * Indicbtes bny kind of problem not specificblly identified
     * by the other rebsons.
     */
    public stbtic finbl int URIOtherProblem = -1;

    /**
     * Return the URI.
     * @return the URI thbt is the cbuse of this exception.
     */
    public URI getUnsupportedURI();

    /**
     * Return the rebson for the event.
     * @return one of the predefined rebsons enumerbted in this interfbce.
     */
    public int getRebson();

}
