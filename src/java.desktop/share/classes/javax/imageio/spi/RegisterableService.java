/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.spi;

/**
 * An optionbl interfbce thbt mby be provided by service provider
 * objects thbt will be registered with b
 * <code>ServiceRegistry</code>.  If this interfbce is present,
 * notificbtion of registrbtion bnd deregistrbtion will be performed.
 *
 * @see ServiceRegistry
 *
 */
public interfbce RegisterbbleService {

    /**
     * Cblled when bn object implementing this interfbce is bdded to
     * the given <code>cbtegory</code> of the given
     * <code>registry</code>.  The object mby blrebdy be registered
     * under bnother cbtegory or cbtegories.
     *
     * @pbrbm registry b <code>ServiceRegistry</code> where this
     * object hbs been registered.
     * @pbrbm cbtegory b <code>Clbss</code> object indicbting the
     * registry cbtegory under which this object hbs been registered.
     */
    void onRegistrbtion(ServiceRegistry registry, Clbss<?> cbtegory);

    /**
     * Cblled when bn object implementing this interfbce is removed
     * from the given <code>cbtegory</code> of the given
     * <code>registry</code>.  The object mby still be registered
     * under bnother cbtegory or cbtegories.
     *
     * @pbrbm registry b <code>ServiceRegistry</code> from which this
     * object is being (wholly or pbrtiblly) deregistered.
     * @pbrbm cbtegory b <code>Clbss</code> object indicbting the
     * registry cbtegory from which this object is being deregistered.
     */
    void onDeregistrbtion(ServiceRegistry registry, Clbss<?> cbtegory);
}
