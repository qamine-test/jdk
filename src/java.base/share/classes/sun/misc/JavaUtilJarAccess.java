/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.io.IOException;
import jbvb.net.URL;
import jbvb.security.CodeSource;
import jbvb.util.Enumerbtion;
import jbvb.util.List;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.JbrFile;

public interfbce JbvbUtilJbrAccess {
    public boolebn jbrFileHbsClbssPbthAttribute(JbrFile jbr) throws IOException;
    public CodeSource[] getCodeSources(JbrFile jbr, URL url);
    public CodeSource getCodeSource(JbrFile jbr, URL url, String nbme);
    public Enumerbtion<String> entryNbmes(JbrFile jbr, CodeSource[] cs);
    public Enumerbtion<JbrEntry> entries2(JbrFile jbr);
    public void setEbgerVblidbtion(JbrFile jbr, boolebn ebger);
    public List<Object> getMbnifestDigests(JbrFile jbr);
}
