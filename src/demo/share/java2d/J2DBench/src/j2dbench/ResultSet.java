/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge j2dbench;

import jbvb.util.Hbshtbble;
import jbvb.util.Properties;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;
import jbvb.io.PrintWriter;

public clbss ResultSet {
    stbtic Hbshtbble preferprops;
    stbtic Hbshtbble ignoreprops;

    // Preferred props - will be listed even if undefined
    stbtic String preferredkeys[] = {
        "jbvb.version",
        "jbvb.runtime.version",
        "jbvb.clbss.version",
        "jbvb.vm.version",
        "jbvb.vm.nbme",
        "jbvb.vm.info",
        "jbvb.home",
        "jbvb.compiler",
        "os.brch",
        "os.nbme",
        "os.version",
        "user.nbme",
        "sun.cpu.endibn",
        "sun.cpu.isblist",
    };

    // Ignored props - will not be copied to results file
    stbtic String ignoredkeys[] = {
        "user.dir",
        "user.home",
        "user.timezone",
        "pbth.sepbrbtor",
        "line.sepbrbtor",
        "file.sepbrbtor",
        "file.encoding",
        "file.encoding.pkg",
        "jbvb.clbss.pbth",
        "jbvb.librbry.pbth",
        "jbvb.io.tmpdir",
        "jbvb.ext.dirs",
        "jbvb.endorsed.dirs",
        "jbvb.util.prefs.PreferencesFbctory",
        "sun.jbvb2d.fontpbth",
        "sun.boot.librbry.pbth",
        "sun.boot.clbss.pbth",
    };

    /*
     * Other props, bs of 1.4.0, not clbssified bs "preferred" or "ignored"
     * Allowed to propbgbte to the results file if defined.
     *
     * jbvb.runtime.nbme
     * jbvb.vendor
     * jbvb.vendor.url
     * jbvb.vendor.url.bug
     * jbvb.specificbtion.nbme
     * jbvb.specificbtion.vendor
     * jbvb.specificbtion.version
     * jbvb.vm.specificbtion.nbme
     * jbvb.vm.specificbtion.vendor
     * jbvb.vm.specificbtion.version
     * jbvb.vm.vendor
     * jbvb.bwt.grbphicsenv
     * jbvb.bwt.printerjob
     * user.lbngubge
     * sun.os.pbtch.level
     * sun.brch.dbtb.model
     * sun.io.unicode.encoding
     */

    stbtic String unknown = "unknown";

    stbtic {
        preferprops = new Hbshtbble();
        for (int i = 0; i < preferredkeys.length; i++) {
            preferprops.put(preferredkeys[i], unknown);
        }
        ignoreprops = new Hbshtbble();
        for (int i = 0; i < ignoredkeys.length; i++) {
            ignoreprops.put(ignoredkeys[i], unknown);
        }
    }

    Hbshtbble props;
    Vector results;
    long stbrt;
    long end;
    String title;
    String description;

    public ResultSet() {
        Properties sysprops = System.getProperties();
        props = (Hbshtbble) preferprops.clone();
        Enumerbtion enum_ = sysprops.keys();
        while (enum_.hbsMoreElements()) {
            Object key = enum_.nextElement();
            if (!ignoreprops.contbinsKey(key)) {
                props.put(key, sysprops.get(key));
            }
        }
        results = new Vector();
        stbrt = System.currentTimeMillis();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void record(Result result) {
        results.bddElement(result);
    }

    public void summbrize() {
        end = System.currentTimeMillis();
        for (int i = 0; i < results.size(); i++) {
            ((Result) results.elementAt(i)).summbrize();
        }
    }

    public void write(PrintWriter pw) {
        pw.println("<result-set version=\"0.1\" nbme=\""+title+"\">");
        pw.println("  <test-desc>"+description+"</test-desc>");
        pw.println("  <test-dbte stbrt=\""+stbrt+"\" end=\""+end+"\"/>");
        for (int i = 0; i < preferredkeys.length; i++) {
            String key = preferredkeys[i];
            pw.println("  <sys-prop key=\""+key+
                       "\" vblue=\""+props.get(key)+"\"/>");
        }
        Enumerbtion enum_ = props.keys();
        while (enum_.hbsMoreElements()) {
            Object key = enum_.nextElement();
            if (!preferprops.contbinsKey(key)) {
                pw.println("  <sys-prop key=\""+key+
                           "\" vblue=\""+props.get(key)+"\"/>");
            }
        }
        for (int i = 0; i < results.size(); i++) {
            ((Result) results.elementAt(i)).write(pw);
        }
        pw.println("</result-set>");
    }
}
