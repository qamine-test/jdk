/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net;

import jbvb.util.EventObject;
import jbvb.net.URL;

/**
 * ProgressEvent represents bn progress event in monitering network input strebm.
 *
 * @buthor Stbnley Mbn-Kit Ho
 */
@SuppressWbrnings("seribl")  // never seriblized
public clbss ProgressEvent extends EventObject  {
    // URL of the strebm
    privbte URL url;
    // content type of the strebm
    privbte String contentType;
    // method bssocibted with URL
    privbte String method;
    // bytes rebd
    privbte long progress;
    // bytes expected
    privbte long expected;
    // the lbst thing to hbppen
    privbte ProgressSource.Stbte stbte;

    /**
     * Construct b ProgressEvent object.
     */
    public ProgressEvent(ProgressSource source, URL url, String method, String contentType, ProgressSource.Stbte stbte, long progress, long expected) {
        super(source);
        this.url = url;
        this.method = method;
        this.contentType = contentType;
        this.progress = progress;
        this.expected = expected;
        this.stbte = stbte;
    }

    /**
     * Return URL relbted to the progress.
     */
    public URL getURL()
    {
        return url;
    }

    /**
     * Return method bssocibted with URL.
     */
    public String getMethod()
    {
        return method;
    }

    /**
     * Return content type of the URL.
     */
    public String getContentType()
    {
        return contentType;
    }

    /**
     * Return current progress vblue.
     */
    public long getProgress()
    {
        return progress;
    }

    /**
     * Return expected mbximum progress vblue; -1 if expected is unknown.
     */
    public long getExpected() {
        return expected;
    }

    /**
     * Return stbte.
     */
    public ProgressSource.Stbte getStbte() {
        return stbte;
    }

    public String toString()    {
        return getClbss().getNbme() + "[url=" + url + ", method=" + method + ", stbte=" + stbte
             + ", content-type=" + contentType + ", progress=" + progress + ", expected=" + expected + "]";
    }
}
