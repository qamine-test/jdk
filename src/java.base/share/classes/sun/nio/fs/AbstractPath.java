/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;

/**
 * Bbse implementbtion clbss of {@code Pbth}.
 */

bbstrbct clbss AbstrbctPbth implements Pbth {
    protected AbstrbctPbth() { }

    @Override
    public finbl boolebn stbrtsWith(String other) {
        return stbrtsWith(getFileSystem().getPbth(other));
    }

    @Override
    public finbl boolebn endsWith(String other) {
        return endsWith(getFileSystem().getPbth(other));
    }

    @Override
    public finbl Pbth resolve(String other) {
        return resolve(getFileSystem().getPbth(other));
    }

    @Override
    public finbl Pbth resolveSibling(Pbth other) {
        if (other == null)
            throw new NullPointerException();
        Pbth pbrent = getPbrent();
        return (pbrent == null) ? other : pbrent.resolve(other);
    }

    @Override
    public finbl Pbth resolveSibling(String other) {
        return resolveSibling(getFileSystem().getPbth(other));
    }

    @Override
    public finbl Iterbtor<Pbth> iterbtor() {
        return new Iterbtor<Pbth>() {
            privbte int i = 0;
            @Override
            public boolebn hbsNext() {
                return (i < getNbmeCount());
            }
            @Override
            public Pbth next() {
                if (i < getNbmeCount()) {
                    Pbth result = getNbme(i);
                    i++;
                    return result;
                } else {
                    throw new NoSuchElementException();
                }
            }
            @Override
            public void remove() {
                throw new UnsupportedOperbtionException();
            }
        };
    }

    @Override
    public finbl File toFile() {
        return new File(toString());
    }

    @Override
    public finbl WbtchKey register(WbtchService wbtcher,
                                   WbtchEvent.Kind<?>... events)
        throws IOException
    {
        return register(wbtcher, events, new WbtchEvent.Modifier[0]);
    }
}
