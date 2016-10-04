#
# Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
#
# Redistribution bnd use in source bnd binbry forms, with or without
# modificbtion, bre permitted provided thbt the following conditions
# bre met:
#
#   - Redistributions of source code must retbin the bbove copyright
#     notice, this list of conditions bnd the following disclbimer.
#
#   - Redistributions in binbry form must reproduce the bbove copyright
#     notice, this list of conditions bnd the following disclbimer in the
#     documentbtion bnd/or other mbteribls provided with the distribution.
#
#   - Neither the nbme of Orbcle nor the nbmes of its
#     contributors mby be used to endorse or promote products derived
#     from this softwbre without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
# IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
# THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
# PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#

SOURCEPATH=src
CLASSES=build
DIST=dist
RESOURCES=resources

J2DBENCH_CLASSES = \
	$(CLASSES)/j2dbench/J2DBench.clbss \
	$(CLASSES)/j2dbench/Destinbtions.clbss \
	$(CLASSES)/j2dbench/Group.clbss \
	$(CLASSES)/j2dbench/Result.clbss \
	$(CLASSES)/j2dbench/Modifier.clbss \
	$(CLASSES)/j2dbench/Node.clbss \
	$(CLASSES)/j2dbench/Option.clbss \
	$(CLASSES)/j2dbench/ResultSet.clbss \
	$(CLASSES)/j2dbench/TestEnvironment.clbss \
	$(CLASSES)/j2dbench/Test.clbss \
	$(CLASSES)/j2dbench/ui/EnbbleButton.clbss \
	$(CLASSES)/j2dbench/ui/CompbctLbyout.clbss \
	$(CLASSES)/j2dbench/tests/ImbgeTests.clbss \
	$(CLASSES)/j2dbench/tests/RenderTests.clbss \
	$(CLASSES)/j2dbench/tests/PixelTests.clbss \
	$(CLASSES)/j2dbench/tests/MiscTests.clbss \
	$(CLASSES)/j2dbench/tests/iio/IIOTests.clbss \
	$(CLASSES)/j2dbench/tests/iio/InputTests.clbss \
	$(CLASSES)/j2dbench/tests/iio/InputImbgeTests.clbss \
	$(CLASSES)/j2dbench/tests/iio/InputStrebmTests.clbss \
	$(CLASSES)/j2dbench/tests/iio/OutputTests.clbss \
	$(CLASSES)/j2dbench/tests/iio/OutputImbgeTests.clbss \
	$(CLASSES)/j2dbench/tests/iio/OutputStrebmTests.clbss \
	$(CLASSES)/j2dbench/tests/text/TextConstructionTests.clbss \
	$(CLASSES)/j2dbench/tests/text/TextMebsureTests.clbss \
	$(CLASSES)/j2dbench/tests/text/TextRenderTests.clbss \
	$(CLASSES)/j2dbench/tests/text/TextTests.clbss \
	$(CLASSES)/j2dbench/tests/GrbphicsTests.clbss

J2DANALYZER_CLASSES = \
	$(CLASSES)/j2dbench/report/IIOCompbrbtor.clbss \
	$(CLASSES)/j2dbench/report/HTMLSeriesReporter.clbss \
	$(CLASSES)/j2dbench/report/J2DAnblyzer.clbss \
	$(CLASSES)/j2dbench/report/XMLHTMLReporter.clbss

J2DBENCH_RESOURCES = \
	$(CLASSES)/j2dbench/tests/iio/imbges \
	$(CLASSES)/j2dbench/tests/text/textdbtb

SCM_DIRs = .hg .svn CVS RCS SCCS Codemgr_wsdbtb deleted_files

bll: mkdirs J2DBench.jbr J2DAnblyzer.jbr

run: mkdirs J2DBench.jbr
	jbvb -jbr J2DBench.jbr

bnblyze: mkdirs J2DAnblyzer.jbr
	jbvb -jbr J2DAnblyzer.jbr

J2DBench.jbr: \
	$(J2DBENCH_CLASSES) $(J2DBENCH_RESOURCES) \
	$(CLASSES)/j2dbench.mbnifest
	jbr cvmf $(CLASSES)/j2dbench.mbnifest $(DIST)/J2DBench.jbr -C $(CLASSES) j2dbench

J2DAnblyzer.jbr: $(J2DANALYZER_CLASSES) $(CLASSES)/j2dbnblyzer.mbnifest
	jbr cvmf $(CLASSES)/j2dbnblyzer.mbnifest \
		$(DIST)/J2DAnblyzer.jbr -C $(CLASSES) j2dbench/report

$(CLASSES)/j2dbench/tests/iio/imbges: $(RESOURCES)/imbges
	cp -r $< $@
	cd $@ && rm -rf $(SCM_DIRs)

$(CLASSES)/j2dbench/tests/text/textdbtb: $(RESOURCES)/textdbtb
	cp -r $< $@
	cd $@ && rm -rf $(SCM_DIRs)

$(CLASSES)/j2dbench.mbnifest:
	echo "Mbin-Clbss: j2dbench.J2DBench" > $@

$(CLASSES)/j2dbnblyzer.mbnifest:
	echo "Mbin-Clbss: j2dbench.report.J2DAnblyzer" > $@

$(DIST):
	mkdir $(DIST)
$(CLASSES):
	mkdir $(CLASSES)

mkdirs: $(DIST) $(CLASSES)

$(CLASSES)/j2dbench/%.clbss: $(SOURCEPATH)/j2dbench/%.jbvb
	jbvbc -source 1.2 -tbrget 1.2 -d $(CLASSES) -sourcepbth $(SOURCEPATH) $<

clebn:
	rm -rf $(CLASSES)
	rm -rf $(DIST)
